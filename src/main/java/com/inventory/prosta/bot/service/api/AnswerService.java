package com.inventory.prosta.bot.service.api;

import com.inventory.prosta.bot.model.answer.AnswerEvent;


public interface AnswerService<T extends AnswerEvent> {

    /**
     * Проверка, является ли сообщенеи в чате ответом на событие.
     *
     * @param answerEvent - объект, содержащий  информацию о запущенном в чате событии,
     *                    которое предполагает получение определнных данных от определенного
     *                    пользователя в чате.
     * @return - возвращает true если сообщение отправленное в чат содержит необходимую информацию от нужного пользвателя.
     */
    boolean eventIsAnswer(T answerEvent);

    /**
     * Выполнить Answer event. В зависимости от типа answerEvent запускает выполнение логики,
     * на основе данных, которые содержатся в сообщении из чата.
     *
     * @param answerEvent - объект, содержащий  информацию о запущенном в чате событии,
     *                    которое предполагает получение определнных данных от определенного
     *                    пользователя в чате.
     */
    void executeAnswerEvent(T answerEvent);

}
