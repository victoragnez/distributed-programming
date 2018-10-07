#include <cpprest/http_client.h>
#include <cpprest/filestream.h>
#include <thread>

using namespace utility;
using namespace web;
using namespace web::http;
using namespace web::http::client;
using namespace concurrency::streams;

//verifica se um arquivo existe
inline bool existsFile (const char * file) {
	struct stat tmpStat;
	return (stat(file, &tmpStat) == 0); 
}

//recebe tam inicial esperado, retorna a string lida com tamanho arbitrario
char* readString(size_t maxTam = 1){
	char *ret, cur;
	size_t len = 0;
	
	ret = (char*)realloc(NULL, sizeof(char)*maxTam);
	
	if(!ret)
		return ret;
	
	while(scanf("%c", &cur)!=EOF && !(cur != '\n' && cur != '\0' && cur != ' '));
	
	ret[len++]=cur;
	
	while(scanf("%c", &cur)!=EOF && cur != '\n' && cur != '\0' && cur != ' '){
		
		ret[len++]=cur;
		
		if(len==maxTam){
			ret = (char*)realloc(ret, sizeof(char)*(maxTam+=16));
			if(!ret) return ret;
		}
	}
	
	ret[len++]='\0';

	return (char*)realloc(ret, sizeof(char)*len);
}

//Le string ate que seja digitado um inteiro valido
int readInt(bool positivo = false){
	while(true){
		char *str = readString(10);
		size_t len = strlen(str);
		long long val = 0;
		bool ok = true;
		if(len > 10 || (!('0' <= str[0] && str[0] <= '9') && (str[0] != '-' || positivo))){
			ok = false;
		}
		bool neg = (str[0] == '-');
		if(ok){
			size_t i = 0;
			if(neg) i++;
			for(; i < len; i++){
				if(!('0' <= str[i] && str[i] <= '9')){
					ok = false;
					break;
				}
				val = (val<<3) + (val<<1) + str[i] - '0';
			}
		}
		
		if(neg)
			val = -val;
		
		if(ok && val >= (1<<31) && val <= ((-1)^(1<<31)))
			return (int)val;
		
		printf("Valor inválido. Digite um valor inteiro de 32 bits");
		if(positivo) printf(" positivo");
		printf("\n");
	}
}

std::string toUpper(std::string & s){
	std::string t(s);
	for(size_t i = 0; i < t.size(); i++)
		if('a' <= t[i] && t[i] <= 'z')
			t[i] = char(t[i] + 'A'-'a');
	return t;
}

const char result[] = "result.html";
const int numTentativas = 5;

int main(){

	std::vector<std::string> tipos({"BinaryTree", "Bubble", "Counting", "Heap", "Insertion", 
		"Merge", "Quick", "Radix", "Random", "Selection", "Stooge"});
	
	printf("Digite 0 para entrada por arquivo json e outro valor para entrada manual\n");
	
	char c;

	scanf(" %c", &c);

	const bool arquivo = (c == '0');	
	std::vector<int> vet;
	std::string tipo, nomeArquivo;
	
	if(!arquivo){
		printf("Digite o tamanho do vetor.\n");
		int n = readInt(true);
		for(int i = 0; i < n; i++){
			printf("Digite o elemento de indice %d\n", i);
			vet.push_back(readInt());
		}

		printf("Digite o tipo de ordenação.\nTipos disponíveis:\n");
		do{
			for(std::string cur : tipos)
				printf("%s\n", cur.c_str());
			
			tipo = std::string(readString());
		
			bool ok = false;
		
			for(std::string cur : tipos){
				if(cur.size() == tipo.size() && toUpper(cur) == toUpper(tipo)){
					ok = true;
					tipo = cur;
					break;
				}
			}
		
			if(ok) break;
		
			printf("Tipo inválido. Tipos disponíveis:\n");
		} while(true);
	}
	
	
	auto stream = std::make_shared<ostream>();

	 // Open stream to output file.
	for(int tentativa = 0; tentativa < numTentativas; tentativa++){
	
	if(existsFile(result))
		remove(result);

	pplx::task<void> requisicao = fstream::open_ostream(U(result)).then([&](ostream arquivoSaida){
		*stream = arquivoSaida;
		
		//Query pelo URL com retorno em JSON:
		if(!arquivo) {
			http_client cliente(U("http://localhost:8080/SortAPI/rest/hello"));
			uri_builder uri(U("/sortArrayJSON"));
			uri.append_query(U("sort"), U(tipo.c_str()));
			
			std::ostringstream lista;
			lista << std::dec;
			
			for(size_t i = 0; i < vet.size(); i++){
				lista << vet[i];
				if(i + 1 < vet.size())
					lista << ",";
			}
			
			std::string tmp = lista.str();
			
			uri.append_query(U("list"), tmp.c_str());
			
			return cliente.request(methods::GET, uri.to_string());
		}
		
		//Query pelo JSON:
		else{
			http_client cliente(U("http://localhost:8080/SortAPI/rest/api"));
			uri_builder uri(U("/sortArrayJSONPOST"));
			
			if(tentativa == 0){
				while(true){
					printf("Digite o nome do arquivo a ser lido.\n");
					nomeArquivo = std::string(readString());
					if(existsFile(nomeArquivo.c_str()))
						break;
					printf("Arquivo não pode ser lido. Digite novamente.\n");
				}
			}
			
			ifstream_t f(nomeArquivo);
			stringstream_t s;
	
			s << f.rdbuf();
			f.close();

			const std::string tmp = s.str();
		
			return cliente.request(methods::POST, uri.to_string(), 
				utility::conversions::to_utf8string(tmp), U("application/json"));
		
		}
	})
	.then([&](http_response resposta){
		auto status = resposta.status_code();
		
		if(status >= 200 && status < 300){
			printf("Requisição sucedida!");
			if(status != 200)
				printf(" Status retornado: %d", status);
			printf("\n");
		}else if(status >= 300 && status < 400){
			printf("Status http de redirecionamento retornado: %d\n", status);
		}else if(status >= 400 && status < 500){
			printf("Status http de erro no cliente! erro: %d\n", status);
		}else if(status >= 500 && status < 600){
			printf("Status http de erro no servidor! erro: %d\n", status);
		}
		else printf("Status http retornado: %d\n", status);
		
		printf("Resposta do servidor salva no arquivo %s\n", result);
		tentativa = numTentativas-1;

		return resposta.body().read_to_end(stream->streambuf());
	})
	.then([&](size_t){
		return stream->close();
	});

	try{
		requisicao.wait();
	}
	catch(const std::exception &e){
		printf("Erro recebido: %s\n", e.what());
		if(tentativa+1 < numTentativas){
			printf("Tentando reconectar em 3s...\n");
			std::this_thread::sleep_for(std::chrono::milliseconds(3000));
		}
	}
	
	}
}
