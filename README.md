# Web Server - Concurrente 
### Autor: Carlos Andrés Ramírez Torres
### Fecha: Viernes, 5 de Junio del 2020
##### LOC/h: 1559/12 = 129.91 LOC/h

## Uso 

Para el desarrollo del proyecto se utilizo **Maven** como una herramienta para la construccion y gestion del mismo, el codigo fue desarrollado con el lenguaje de programación **Java**.

Utilizar `mvn package` para la generacion del fichero .jar con los .class compilados.

![Texto alternativo]()

Una vez realizado el `mvn package` el codigo estara listo para ser utilizado bajo la siguiente estructura.

Para ejecutar el programa se utilizara desde la terminal el siguiente comando en terminal dando inicio al servidor web:

`java -cp target/classes edu.escuelaing.arsw.app.App.App`
 
por ejemplo:

![Texto alternativo]()

Como se puede observar el servidor web inicia y se muestra un mensaje que indica que esta esuchando por el **puerto 35000**:

A continuacion se puede proceder a la utilizacion mediante el browser, para esto, se debe poner la siguiente URL para solicitar diferentes archivos desde el cliente al servidor:

`http://127.0.0.1:3500/<path del archivo solicitado>`

Los archivos a los cuales el servidor responde tienen las siguientes extensiones:

* Javascript `.js`
* HTML `.html`
* CSS `.css`
* PNG `.png`
* JPG `.jpg`
* JPEG `.jpeg`
* SVG `.svg`

Algunos de los archivos de los que dispone el servidor son:

* `http://127.0.0.1:3500/index.html`
* `http://127.0.0.1:3500/pngfile.png`
* `http://127.0.0.1:3500/jpgfile.jpg`

Por ejemplo:

![Texto alternativo]()

Cuando el **PATH** ingresado por el usuario es incorrecto debido a que el archivo no existe se enviara un mensaje de error **404** como se muestra a continuacion:

![Texto alternativo]()


## Diagrama

* Aplicación 

![Texto alternativo]()

El diagrama muestra que la clase **App** recibe el argumento, en este caso se trata de la ruta del archivo al cual se le realizara el calculo correspondiente a la **media** y **desviación estandar**, la clase **App** crea una instancia de la clase **CalculateMeanAndDeviationService** cuya finalidad es la lectura del archivo encontrado y mediante los metodos **calculateMean** y **calculateStandardDeviation** envia una **MyLinkedList** que contiene los datos del archivo leido anteriormente, para ser utilizada por la interfaz ***Calculate*** que con el uso de polimorfismo obtendra el calculo correspondiente a la media o desviación estandar, esta estructura permitira posibles cambios que se deseen implementar como en el caso de que el usuario quiera obtener unicamente un valor y a su vez la extension del software para otros calculos.

## Pruebas 

Se han realizado un **total de 5 JUnit test** con el fin de garantizar que el servidor encuentre y retorne los diferentes archivos solicitados mediante la **URL** adicionalmente mediante **JMeter** se realizaron **Pruebas de carga** sobre el servidor que buscan verificar la respuesta concurrente del mismo, las pruebas se explicaran a continuacion.

* Lectura de archivos existentes.
* Lectura de archivos no encontrados.
* Pruebas de carga sobre el servidor.

### Pruebas implementadas

![]()

### Utilizando el comando de `mvn test`

![]()

### JMeter

Se realizaron multiples pruebas de carga utilizando diferentes configuraciones, algunas de ellas son:

* **100 usuarios en 1 segundo.**
* **500 usuarios en 1 segundo.**
* **1000 usuarios en 1 segundo.**

Las pruebas anteriores resultaron satisfactorias, sin embargo para la prueba de **1500 usuarios en 1 segundo** el servidor comenzo a tener incombenientes no significativos teniendo en cuenta la prueba de estres a la que fue sometido.

**La configuracion fue la siguiente:**

![]()

* `http://127.0.0.1:3500/index.html` 1500 usuarios en 1 segundo. resultados:

**Tabla:**

![](https://github.com/CAndresRa/ARSW-CuartoLaboratorio/blob/master/imgReadme/Tablehtml.png)

Como se puede observar, de las 1500 solicitudes realizadas fallaron 3, es un porcentaje minimo sin embargo se denota una diferencia significativa en los bytes de la solicitud fallida, lo que puede significar que algunos archivos contenidos en el **index.html** no se logran escribir correctamente, esto puede ser a que es una extension no contemblada en el modelo.

**Grafica:**

![](https://github.com/CAndresRa/ARSW-CuartoLaboratorio/blob/master/imgReadme/GraficosHtml.png)

La grafica muestra el comportamiento de las solicitudes y factores en el manejo de los paquetes de los archivos involucrados en la prueba durante 1 segundo.

**Servidor:**

![](https://github.com/CAndresRa/ARSW-CuartoLaboratorio/blob/master/imgReadme/TerminalHtml.png)

El servidor no se vio interrumpido en el transcurso de la prueba y soluciono un total de 1496 solicitudes.

**Datos Adicionales:**

Adicionalmente utilizando Jmeter se puede obtener informacion adicional como:

* Encabezado

![](https://github.com/CAndresRa/ARSW-CuartoLaboratorio/blob/master/imgReadme/EncabezadoHTML.png)

* Resumen del resultado 

![](https://github.com/CAndresRa/ARSW-CuartoLaboratorio/blob/master/imgReadme/SamplerResultHtml.png)

* Cuerpo del resultado

![](https://github.com/CAndresRa/ARSW-CuartoLaboratorio/blob/master/imgReadme/ResponseDataIndexHtml.png)


* Utilizando `http://127.0.0.1:3500/pngfile.png` Los resultados fueron satisfactorios, en total de 1500 solicitudes en 1 segundo se realizaron de forma correspondiente.

**Tabla**

![](https://github.com/CAndresRa/ARSW-CuartoLaboratorio/blob/master/imgReadme/tablePng.png)

Se muestran las 1500 solicitudes realizadas al archivo correspondiente.

![](https://github.com/CAndresRa/ARSW-CuartoLaboratorio/blob/master/imgReadme/GraficaPNG.png)

Se puede observar que el envio de paquetes se muestra de forma escalonada, se debe a la forma en la que se realiza la escritura de imagenes en el servidor.

**Servidor** 

![](https://github.com/CAndresRa/ARSW-CuartoLaboratorio/blob/master/imgReadme/Terminal1500.png)

El servidor respondio las 1500 solicitudes satisfactoriamente.

**Datos Adicionales**

Adicionalmente utilizando Jmeter se puede obtener informacion adicional como:

* Encabezado

![](https://github.com/CAndresRa/ARSW-CuartoLaboratorio/blob/master/imgReadme/hEADERpNG.png)

* Resumen del resultado 

![](https://github.com/CAndresRa/ARSW-CuartoLaboratorio/blob/master/imgReadme/SampleResulPNG.png)

* Cuerpo del resultado

![](https://github.com/CAndresRa/ARSW-CuartoLaboratorio/blob/master/imgReadme/BodyPng.png)

**Adicionalmente se realizo la prueba de carga de 1500 solicitudes en 1 segundo con un archivo html sencillo y fijo, obteniendo una prueba satisfactoria**
