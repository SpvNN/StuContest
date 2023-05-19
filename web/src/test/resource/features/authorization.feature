Авторизация пользователя в приложении 

# language: ru

@all

Функция: Авторизация в приложении

Given
Приложение должно спросить данные для входа пользователя
And
Приложение должно выдать предупреждение, если пользователь ввел неверные логин и/или пароль

Предыстория:

Given
Допустим пользователь открывает приложение
And
И приложение выдает сообщение о необходимости авторизоваться

@correct

Сценарий: Успешная авторизация

When
Если пользователь вводит верные данные для входа
Then
То приложение отображает личный кабинет пользователя

@fail

Сценарий: Ошибка авторизации

When
Если пользователь вводит неверные логин и/или пароль
Then
То приложение выдает сообщение, что данные некорректны