# OrdersDB

API desenvolvida para o projeto [Orders Manager](https://github.com/gabrielbrub/orders_manager).

### Pré-requisitos

```
JDK 8
MySQL 8
```

## Uso

* Crie o usuário 'OrdersManager'@'localhost' (sem senha) com grants no MySQL:

```
 create user 'OrdersManager'@'localhost' identified by '';
 grant all privileges on ordersdb.* to 'OrdersManager'@'localhost';
 ```
 Ou especifique um usuário existente no application.properties.
 
 Para visualizar os endpoints disponíveis, acesse
 
 ```
http://localhost:8080/swagger-ui.html
 ```

## Feito com

* [IntelliJ IDEA](https://www.jetbrains.com/idea/)

## Autor

* **Gabriel Rubião** - [gabrielbrub](https://github.com/gabrielbrub)

                                                                     
                 
