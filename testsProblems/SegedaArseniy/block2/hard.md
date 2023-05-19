Задача 3 (сложный):
Даны n неотрицательные целые числа, представляющие карту высот, где ширина каждой полосы равна 1, вычислить, сколько воды она может удержать после дождя.

Пример 1:
Ввод: высота = [0,1,0,2,1,0,1,3,2,1,2,1]
Вывод: 6
Объяснение: Приведенная выше карта высот (черная часть) представлена массивом [0,1 ,0,2,1,0,1,3,2,1,2,1]. В этом случае задерживается 6 единиц дождевой воды (синяя секция).
Пример 2:
Ввод: высота = [4,2,0,3,2,5]
Вывод: 9
 
Ограничения:
1.	n == height.length
2.	1 <= n <= 2 * 10^4
3.	0 <= height[i] <= 10^5