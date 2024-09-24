package io.job4j.concurrent;

 /* В приведённом коде синглтона с использованием "double-checked locking" есть потенциальная проблема,
 связанная с кэшированием и оптимизациями, которые могут быть выполнены JVM.
 Без модификатора volatile для переменной instance Java компилятор и процессор могут изменить порядок инструкций,
 так что другой поток увидит частично созданный объект. Это может привести к таким ситуациям,
 когда другой поток получит ссылку на объект DCLSingleton, который ещё не завершил инициализацию.
 Чтобы исправить это, мы можем использовать volatile, который гарантирует,
 что все изменения в переменной instance будут видны всем потокам сразу.*/

public final class DCLSingleton {

    private static volatile DCLSingleton instance;

    private DCLSingleton() {
    }

    public static DCLSingleton getInstance() {
        if (instance == null) {
            synchronized (DCLSingleton.class) {
                if (instance == null) {
                    instance = new DCLSingleton();
                }
            }
        }
        return instance;
    }
}