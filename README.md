## Criar o projeto Java 21

* > Criar o Dockerfile

* > Subir no seu git

* > entrar na pasta da vps
cd /root/infra-proxy

* > fazer o git clone
git clone https://github.com/srodrigo28/java-vps.git

* > atualize o service
nano docker-compose.yml
  GNU nano 7.2                           docker-compose.ymlx
      - rede_interna

# --- SITE 2 (API - PHP 8.4) ---
  site2:
    image: php:8.4-apache
    container_name: site2
    volumes:
      - ./site2:/var/www/html
    networks:
      - rede_interna

# --- SEU APP JAVA (Repo: java-vps) ---
  app-java:
    build: ./java-vps
    container_name: java-producao
    restart: always
    environment:
      - SPRING_PROFILES_ACTIVE=prod
    networks:
      - rede_interna

* > Rodar o Docker build
docker compose up -d --build