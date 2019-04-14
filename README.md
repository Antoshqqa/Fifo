# Fifo

#### Данные хранятся в PostgreSQL, при первом запуске проекта будут созданы необходимые таблицы в БД посредством FlyWay  
#### Для того, чтобы развернуть СУБД нужно: 
##### В терминале выполнить команды:  
* sudo -i   
* docker volume create pgdata  
* docker run --rm --name pgStatic -v pgdata:/var/lib/postgresql/data -p 5435:5432 -e POSTGRES_PASSWORD=mysecretpassword -d postgres:latest  
    ###### Примечание: Вместо пароля mysecretpassword и порта 5435 можно указать свои значения, они в последующем 
    ###### будут использованы Hibernate для подключения к БД  
* docker exec -it pgStatic bash  
* su - postgres  
* psql  
* create database fifo; (либо любое другое название)  
* \q  
* exit  
* exit  
 
#### Для сборки и запуска проекта необходимо отредактировать файл ./config/hibernate.cfg.xml:  
##### Заменить в следующих строках данные на ваши:  
* property name="hibernate.connection.password" ВСТАВИТЬ ВАШ ПАРОЛЬ СЮДА  
* property name="hibernate.connection.url" jdbc:postgresql://ВСТАВИТЬ СЮДА URL ДО БД  
* В случае, если БД fifo создана не от пользователя postgres, то необходимо изменить имя пользователя в следующей строчке:  
  property name="hibernate.connection.username" ИМЯ ПОЛЬЗОВАТЕЛЯ  

##### Выполнить следующие действия:  
##### Для запуска проекта необходимо, чтобы в директории с jar-файлом находилась директория ./config, содержащая  
##### конфигурационный файл hibernate.cfg.xml
* В терминале перейти в директорию с исходным кодом и выполнить команду:    
    Первые сборка и запуск должны производиться командой mvn -DskipTests=true clean package assembly:single  поскольку в БД еще не существует необходимых таблиц, они будут инициализированы при старте программы 
    Последующие сборки могут быть запущены командой mvn clean package assembly:single.  
    
* cp target/Fifo-jar-with-dependencies.jar .
* Запустить проект командой    java -jar Fifo-jar-with-dependencies.jar

#### После завершения тестирования проекта можно удалить контейнер с СУБД и том для хранения данных следующими командами:  
##### В терминале выполнить:  
* sudo -i  
* docker stop pgStatic  
* docker rmi postgres:latest  
* docker volume rm pgdata  
   
### Пример корректных команд:  
* newproduct Samsung Galaxy Ace 3
* purchase Samsung Galaxy Ace 3 15 35000 14.04.2019
* demand Samsung Galaxy Ace 3 10 50000 14.04.2019
* salesreport Samsung Galaxy Ace 3 14.04.2019
