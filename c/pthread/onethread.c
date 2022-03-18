//every process spawns one thread which executes main()
#include<stdio.h>
#include<pthread.h>

void *start_routine(void *arg)
{
    printf("task executed\n");
    return NULL;
}

int main(int argv, char *argc)
{
    pthread_t tid;
    void *arg;
    int ret;
    // ret = pthread_create(&tid, &tattr, start_routine, arg);
    ret = pthread_create(&tid, NULL, start_routine, arg);

    ret = pthread_join(tid,NULL);
    return 0;
}