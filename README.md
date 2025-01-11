# gsk-16-mock-server

REST API сервис для тестирования. Подменяет запросы к реальным сервисам на заранее установленную заглушку.
Предусмотрена возможность проксирования запроса на целевой сервис в случае отсутствии подходящей заглушки.

Сборка
------
Для сборки дистрибутива к корневом каталоге репа запускается команда:

```batch
./gradlew build -x test
```

После сборки в под-каталоге `/build/libs/` появится файл `gsk-16-mock-server-1.0.0.jar`

Запуск
------
Взять файл `readings-0.0.1-SNAPSHOT.jar`, сформированный на этапе сборки и запустить приложение:

```batch
java -jar gsk-16-mock-server-1.0.0.jar
```
Приложение использует MongoDB для хранения заглушек ответов от сервиса.
Для корректной работы на хосте где поднимается сервис необходимо наличие доступа к работающему серверу mongod

Проверка
--------
При нормальном запуске приложение должно корректно реагировать на запрос

```batch
curl --location --request GET 'http://localhost:8080/management/health'
```
т.е. оно должно выдать 200 OK

Использование
------
1. В конфигурационном файле `application.properties` в секции mappings нужно прописать пробросы в целевые сервисы.
Имеено в них будет осуществлятся запрос в случае отсутствия подходящей заглушки. Можно также это сделать через API.
2. Мок сервер поднят на порту 8088, поэтому при использовании целевые запросы нужно отправлять на `{{host}}:8088/`
3. Сервис предоставляет возможность получить список всех заглушек, добавить новую, удалить по id.

получить список всех заглушек
```batch
curl --location --request GET 'http://localhost:8080/wiremock/mappings'
```

добавить заглушку
```batch
curl --location --request POST 'http://localhost:8080/wiremock/mappings' \
--header 'Content-Type: application/json' \
--data-raw '{
  "request": {
    "method": "GET",
    "urlPath": "/yandex"
  },
  "response": {
        "headers": {
            "Content-Type": "application/json; charset=utf-8"
        },
        "jsonBody": {
            "rates": 42
        }
    },
    "metadata": {
        "name": "Заглушка Yandex"
    }
}'
```

удалить заглушку по id
```batch
curl --location --request DELETE 'http://localhost:8080/wiremock/mappings/e311d473-a139-4d0d-b0c5-3ea796b51d74'
```

Дополнительно по конфигурации сервера
------
https://wiremock.org/