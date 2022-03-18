v hello.v
./hello

v run hello.v

v -o hello.c hello.v
gcc hello.c
./a.out

clang hello.c
./a.out

v -o hello.js hello.v
node hello.js

v run hello.vsh
v hello.vsh
