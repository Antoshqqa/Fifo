# Fifo

#Данные хранятся в PostgreSQL, для того, чтобы развернуть СУБД нужно:  #
##В терминале выполнить команды:  ##
###sudo -i  ###  
###docker volume create pgdata  ###
  docker run --name pgStatic -v pgdata:/var/lib/postgresql/data -p 5435:5432 
                     -e POSTGRES_PASSWORD=mysecretpassword -d postgres:latest
    Примечание: Вместо пароля mysecretpassword и порта 5435 можно указать свои значения, они в последующем
    будут использованы Hibernate для подключения к БД
  --docker exec -it pgStatic bash
  --su - postgres
  --psql
  --create database fifo
  --\q
  --exit
  --exit

Необходимо отредактировать файл ./config/hibernate.cfg.xml:
- Заменить в следующих строках данные на ваши:
  --<property name="hibernate.connection.password">ВСТАВИТЬ ВАШ ПАРОЛЬ СЮДА</property>
  --<property name="hibernate.connection.url">jdbc:postgresql://ВСТАВИТЬ СЮДА URL ДО БД</property>
- В случае, если БД fifo создана не от пользователя postgres, то необходимо изменить имя пользователя в следующей строчке:
  --<property name="hibernate.connection.username">ИМЯ ПОЛЬЗОВАТЕЛЯ</property>

Для сборки и запуска проекта необходимо выполнить следующие действия:
- В командной строке/терминале выполнить команду:
  --mvn clean package assembly:single
 
После завершения тестирования проекта можно удалить контейнер с СУБД и том для хранения данных следующими командами:
- В терминале выполнить:
  --sudo -i
  --docker stop pgStatic
  --docker rm pgStatic
  --docker rmi postgres:latest
  --docker volume rm pgdata
   
  