1. В приложении используется БД Postgres.
2. Перед запуском приложения необходимо создать схему в БД postgres:
- запустить терминал PSQL;
- подключиться к БД (пароль - admin)
- выполнить команду create schema banktest;
3. Запустить приложение ClientCardApiSpringBootApplicationTests
4. В браузере открыть http://localhost:8080/app
5. В поле ввода ввести login - admin, password - qwerty.
6. Запустить тесты, находящиеся в папке tests/java/ru.sberbank/controllers  и tests/java/ru.sberbank/rest для проверки функционала.

=====================================================================================================================

Реализованный фуннционал:
- регистрация новых клиентов;
- возможность добавить карту;
- пополнение баланса одной карты;
- проверка наличия необходимой суммы на карте для списания;
- возможность создать личный кабинет пользователя (просмотр данных пользователя и принадлежащих ему карт)
- возможность выполнять переводы с карты на карту (как между своими счетами и картами, так и другому клиенту)
- интеграционные тесты для проверки работы контроллеров.
- форма главной страницы
- авторизация с помощью Spring Sequrity;

====================================================================================================================

Не реализованный фунционал (TODO)
- страница регистрации
- регистрация с помощью Spring Sequrity;
- после добавления Spring Sequrity не проходят REST тесты, дописать заглушку
