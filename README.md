# Sistema de Corrección y traducción



# Introduccion

El sistema cumple una doble función, por una parte, ante la entrada de una palabra que no aparece en el corpus, busca las palabras que más se le parecen, para ello usa distancias entre las palabras concretamente la distancia de Levenshtein [https://en.wikipedia.org/wiki/Levenshtein\_distance](https://en.wikipedia.org/wiki/Levenshtein_distance)

La distancia de Levenshtein es también llamada distancia de edición o distancia entre palabras. Esta distancia mide el número mínimo de operaciones para transformar una cadena de caracteres en otra cadena de caracteres. Las operaciones válidas para esta medida son la inserción, la eliminación y la sustitución de un carácter. La distancia de Levenshtein es una generalización de la distancia de Hamming. En la distancia de Hamming ambas cadenas tienen que tener la misma longitud, y por lo tanto, solo admite la operación de sustitución.

El algoritmo usa una estructura BK-tree para cargar el corpus ( [https://en.wikipedia.org/wiki/BK-tree](https://en.wikipedia.org/wiki/BK-tree) ) .

Junto con la aplicación se suministran un corpus para el español (corpus\_es.txt) y otro para el inglés(corpus\_en.txt). Los corpus deben estar en una carpeta llamada corpus que se tiene que encontr ar en la misma carpeta de la aplicación. Si se desea añadir un nuevo corpus, se tendrá que crear un fichero llamado corpus\_LC.txt, donde LC es el código del lenguaje al que pertenece el corpus.

La idea de este algoritmo es servir de corrector para una persona escribiendo en un lenguaje que no es el suyo, cuando escribe una palabra mal, el corrector le sugiere correcciones y además le proporciona la traducción a su idioma nativo de cada una de las sugerencias para que escoja la más adecuada.

# Funcionamiento

Para usar la aplicación se necesita tener instalado el runtime de Scala, la aplicación consta de un solo jar (es un Fat Jar, que incluye todas las librerías que se usan).

Sintáxis:

scala Spell-Checker-1.0.0.jar &lt;corpusLang&gt; &lt;translationLang&gt; &lt;words&gt;_

Los parámetros que acepta son los siguientes

- corpusLang :         Código del lenguaje del corpus
- translationLang :         Código del lenguaje del que queremos las traducciones
- words :         Lista de palabras, separadas por espacios en blanco, que queremos procesar.

# Traducción

Para la traduccíon se ha usado una API de libre acceso, para el uso de la misma solo se requiere solicitar una API Key (es gratuito). La información relativa a esta API, así como el modo de obtener una API Key la podemos encontrar en la siguiente url:

[https://tech.yandex.com/translate/](https://tech.yandex.com/translate/)

# Ejemplos

A continuación, se listan algunos ejemplos de uso y sus resultados

## 1) Usando el corpus del lenguaje inglés (en) y con traducción a diversos idiomas.

### Español (es)

D:\Temp\Deploy\SpellChecker&gt;scala Spell-Checker-1.0.0.jar en es spelling marc tesk

spelling &gt; selling[venta] spelling[ortografía] swelling[hinchazón]

marc &gt; march[marzo] mary[maría] mars[marte] mark[marca] mare[mare] marco[marco] mac[mac] arc[arco]

tesk &gt; desk[escritorio] test[prueba] task[tarea]

### Italiano (it)

D:\Temp\Deploy\SpellChecker&gt;scala Spell-Checker-1.0.0.jar en it spelling marc tesk

spelling &gt; selling[vendita] spelling[ortografia] swelling[gonfiore]

marc &gt; march[marzo] mary[maria] mars[marte] mark[mark] mare[mare] marco[marco] mac[mac] arc[arco]

tesk &gt; desk[escursioni] test[prova] task[attività]

### Francés (fr)

D:\Temp\Deploy\SpellChecker&gt;scala Spell-Checker-1.0.0.jar en fr spelling marc tesk

spelling &gt; selling[vente] spelling[l&#39;orthographe] swelling[l&#39;enflure]

marc &gt; march[mars] mary[marie] mars[mars] mark[marque] mare[mare] marco[marco] mac[mac] arc[arc]

tesk &gt; desk[bureau] test[test] task[tâche]

### Alemán (de)

D:\Temp\Deploy\SpellChecker&gt;scala Spell-Checker-1.0.0.jar en de spelling marc tesk

spelling &gt; selling[Verkauf] spelling[Rechtschreibung] swelling[Schwellung]

marc &gt; march[März] mary[mary] mars[mars] mark[mark] mare[mare] marco[marco] mac[mac] arc[arc]

tesk &gt; desk[Schreibtisch] test[test] task[Aufgabe]

### Portugués (pt)

D:\Temp\Deploy\SpellChecker&gt;scala Spell-Checker-1.0.0.jar en pt spelling marc tesk

spelling &gt; selling[venda] spelling[ortografia] swelling[inchaço]

marc &gt; march[março] mary[maria] mars[marte] mark[marca] mare[mare] marco[marco] mac[mac] arc[arco]

tesk &gt; desk[secretária] test[teste] task[tarefa]



## 1) Usando el corpus del lenguaje español (es) y con traducción a diversos idiomas.

### Inglés (en)

D:\Temp\Deploy\SpellChecker&gt;scala Spell-Checker-1.0.0.jar es en comienso sallida inprimir

comienso &gt; comiendo[eating] comienzo[beginning] compenso[compensate] comiensa[comiensa]

sallida &gt; salpida[salpida] fallida[failed] salida[output] pallida[pallida]

inprimir &gt; imprimir[print]

### Holandés (nl)

D:\Temp\Deploy\SpellChecker&gt;scala Spell-Checker-1.0.0.jar es nl comienso sallida inprimir

comienso &gt; comiendo[eten] comienzo[begin] compenso[compenseren] comiensa[comiensa]

sallida &gt; salpida[salpida] fallida[mislukt] salida[uitgang] pallida[pallida]

inprimir &gt; imprimir[print]

### Polaco (pl)

D:\Temp\Deploy\SpellChecker&gt;scala Spell-Checker-1.0.0.jar es pl comienso sallida inprimir

comienso &gt; comiendo[jedli] comienzo[pocz?tek] compenso[compenso] comiensa[comiensa]

sallida &gt; salpida[salpida] fallida[zawiesza] salida[wyj?cie] pallida[pallida]

inprimir &gt; imprimir[drukowanie]