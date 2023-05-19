Учитывая целое число n, верните массив строк answer( с индексом 1 ), где :

answer[i] == "FizzBuzz"если iделится на 3и 5.
answer[i] == "Fizz"если iделится на 3.
answer[i] == "Buzz"если iделится на 5.
answer[i] == i(в виде строки), если ни одно из приведенных выше условий не выполняется.
 

Example 1:
Input: n = 3
Output: ["1","2","Fizz"]
Example 2:
Input: n = 5
Output: ["1","2","Fizz","4","Buzz"]
Example 3:
Input: n = 15
Output: ["1","2","Fizz","4","Buzz","Fizz","7","8","Fizz","Buzz","11","Fizz","13","14","FizzBuzz"]
 

Ограничения:
1 <= n <= 104