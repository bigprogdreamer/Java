package main.java.ru.sgu;

import java.util.Random;

class C {
    String generateRandomString() {
        byte[] randomBytes = new byte[10];
        Random random = new Random();
        for (int i = 0; i < 10; ++i)
            randomBytes[i] = (byte)(random.nextInt(94) + 33);
        return new String(randomBytes);
    }

    void executeWithString() {
        String resultString = "";
        for (int i = 0; i < 100_000; i++)
            resultString = resultString + generateRandomString();
    }

    void executeWithStringBuilder() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 100_000; i++)
            stringBuilder.append(generateRandomString());
    }

    void executeWithStringBuffer() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 100_000; i++)
            stringBuffer.append(generateRandomString());
    }

    long getExecutionTime(int method) {
        long startTime = System.currentTimeMillis();
        switch (method) {
            case 1:
                this.executeWithString();
                break;
            case 2:
                this.executeWithStringBuilder();
                break;
            case 3:
                this.executeWithStringBuffer();
                break;
        }
        return System.currentTimeMillis() - startTime;
    }

    public void start() {
        C speedTest = new C();
        System.out.println("Время работы программы при использовании String: "
                + speedTest.getExecutionTime(1) + "ms");
        System.out.println("Время работы программы при использовании StringBuilder: "
                + speedTest.getExecutionTime(2) + "ms");
        System.out.println("Время работы программы при использовании StringBuffer: "
                + speedTest.getExecutionTime(3) + "ms");
    }
    public static void main(String[] args) {
        C instance = new C();
        instance.start();
    }
}
