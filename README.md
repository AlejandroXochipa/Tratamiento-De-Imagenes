# Tratamiento-De-Imagenes
Programa con interfaz grafica para el tartamiento de imagenes, basado en la arquitectura MVC.  
Dentro del programa se manejan filtros de restauracion de imagenes, operaciones logicas, operaciones morfologicas, filtros de frecuencia, conversion de modelos, 
filtros de binarizacion, y la obtencion del histograma de la imagen origen. 
Especificaciones:
-La imagen a tratar debe de tener dimensiones iguales (Ejemplo: 1770*1770).
-Antes de aplicar cualquier filtro, debe de aplicarse el filtro de conversion de escala de grises, 
-El programa se maneja en ciertas ocasiones dependiendo el factor variante con metricas de 0 a 255 siendo el valor de cada pixel o en su defecto del 0 al 1.
-Para aplicar los filtros de operaciones logicas, se necesitan 2 imagenes(imagen origen, e imagen secundaria) con mismas dimensiones. 
-Dentro de la seleccion de filtros de frecuencia en la opcion de "punto extra", se hace referencia a un filtro para la mejoria de una radiografia y se pueda apreciar 
 mucho mejor los detalles en la imagen. Se utilizo un filtro de ruidoGaussiano para la eliminacion de impurezas dentro de la imagen. La imagen obtenida del resultado
 de aplicar el filtro se reduce en un 25%, esto se da porque se trabaja con la transformada de Laplace.
-La libreria que se utiliza para la interfaz 
