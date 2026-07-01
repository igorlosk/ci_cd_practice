<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Деплой</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
        }
        .container {
            text-align: center;
            background: white;
            padding: 50px 60px;
            border-radius: 20px;
            box-shadow: 0 20px 60px rgba(0,0,0,0.3);
        }
        .success-icon {
            font-size: 80px;
            color: #4CAF50;
            margin-bottom: 20px;
        }
        h1 {
            color: #2c3e50;
            font-size: 36px;
            margin: 0 0 10px 0;
        }
        .sub-message {
            color: #7f8c8d;
            font-size: 18px;
            margin-top: 10px;
        }
        .timestamp {
            color: #95a5a6;
            font-size: 14px;
            margin-top: 20px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="success-icon">✅</div>
    <h1>Деплой успешно завершен!</h1>
    <p class="sub-message">Приложение успешно развернуто и готово к работе</p>
    <p class="timestamp">
        Время деплоя: <%= new java.util.Date() %>
    </p>
</div>
</body>
</html>