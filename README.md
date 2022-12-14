## ProstaHolyBot

[Попробовать бота](https://t.me/prosta_holy_bot)
___

### Описание

Телеграмм-бот, который умеет автоматически отправлять поздравления с праздниками и пожелания доброго утра/спокойной ночи
в групповые чаты.
После настройки даты рождения для пользователей чата, бот так же сможет поздравлять с Днем Рождения.

По просьбам пользователей в бот была добавлена функция отправки случайных изображений котиков по запросу.
___

### Требования к окружению

1) Необходимо запустить сервер БД postgres
2) Зарегистрировать бота у [@BotFather](https://t.me/BotFather)
3) Сконфигурировать переменные окружения в application.properties
4) Назначить корректный telegrambot.webHookPath в application.properties

```
Для получения обновлений от сервера telegram, webHookPath должен 
соответвтвовать url адресу сервера, на котором запущен бот
```

5) Для запуска без наличия собственного dns имени хоста можно воспользоваться [ngrok](https://ngrok.com)

- После установки ngrok выполнить

```bash
 ngrok http 5606
``` 

- Скопировать назначенный url в переменную telegrambot.webHookPath

___

### Запуск проекта локально

```bash
./gradlew bootRun
```

### Сборка docker образа

```bash
docker build -t prosta-holy-bot .
```
