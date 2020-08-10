# pomelo-ddd
# 领域驱动通用语义化tools

### demo
pomelo-example
```java
    public static void main(String[] args) {


        Student student = PomeloUtil.peel(StudentAggregate.class)
                .load("1")
                .command(
                        AttendYuWenKe
                                .builder()
                                .chapter("Chapter_1")
                                .score(2)
                                .build()
                );

        logger.info("over");
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(student);

        ThreadPoolUtil.shutdown();

    }
```