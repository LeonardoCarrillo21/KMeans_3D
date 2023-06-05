# KMeans_3D
algoritmo  de clasificación KMeans 3D

## Contenido
- [Descripcion](#descripcion)
- [Carcateristicas](#caracteristicas)
- [Video Muestra](#video)
- [Requisitos](#requisitos)
- [Instalación](#instalacion)
- [Uso](#uso)

## Descrpcion <a name="descripcion"></a>
El objetivo del algoritmo K-means es agrupar los datos en K grupos diferentes, donde K es un número predefinido por el usuario. Cada grupo se representa mediante su centroide, que es el punto medio o promedio de todos los puntos pertenecientes a ese grupo. El algoritmo se basa en la minimización de la suma de las distancias al cuadrado entre cada punto y el centroide de su grupo correspondiente.

## Video Muestra<a name="video"></a>

https://github.com/LeonardoCarrillo21/KMeans_3D/assets/83992677/9b73a994-ef95-4280-95d0-481b0553d61e

<hr>

## Caracteristicas <a name="caracteristicas"></a>

### Interfaz
  - El numero de individuos puede superar los 10 000 000 (depende el equipo de computo), este esta definido en input "items".
  - el numero de clases maximo esta definido en input "k".
  - boton remake para reiniciar el programa y la interfaz.
  - botón Go para recalcular los centroides en cada iteración.
  - inputs de coordenadas para ingresar los centroides.
  - Boton de agrega para insertar los centroides en coordenadas aleatorias cada vez que se agrega una nueva.

### Algoritmo K Means:
  1. Seleccionar el valor de K, que indica el número de grupos en los que se desea dividir los datos.
  2. Inicializar aleatoriamente los centroides de los K grupos (preudoaleatorio en este caso, ya que el usuario decide donde colocar los centroides inicialmente).
  3. Asignar cada punto de datos al grupo cuyo centroide esté más cerca.
  4. Recalcular los centroides de cada grupo como el promedio de los puntos asignados a ese grupo.
  5. Repetir los pasos 3 y 4 hasta que los centroides ya no cambien o se alcance un criterio de parada.

## Requisitos <a name="requisitos"></a>
- un equipo de computo que soporte Apache NetBeans
- libreria Jmathplot.jar

## Instalacion <a name="instalacion"></a>
- Apache NetBeans
- importar la libreria desde la configuración de NetBeans

## Uso <a name="uso"></a>
1. Se seleccionan los datos iniciales: "items" (elementos), "k" (clases).
2. agregar centroides.
3. clic en el boton Go.
