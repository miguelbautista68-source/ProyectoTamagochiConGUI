#Tamagochi proyecto con GUI
Simulador de Tamagotchis Interactivos
Hacer un programa orientado a objetos en Java para crear un simulador interactivo de mascotas virtuales Tamagotchi. La aplicación permitirá a los usuarios crear y cuidar diferentes tipos de Tamagotchis, cada uno con sus propias características, necesidades y comportamientos únicos. Los Tamagotchis podrán ser de diferentes tipos, por ejemplo, una muñeca, un perro, un gato o un cuyo. Con funcionalidades similares y características en común
Objetivo.
A través de la implementación de este proyecto, el alumno demostrará los principios fundamentales de la programación orientada a objetos, la abstracción, la encapsulación, la herencia y el polimorfismo..
Acciones del programa
Creación de Tamagotchis: Los usuarios pueden crear diferentes tipos de Tamagotchis, como muñecas, perros, gatos y cuyos (con la posibilidad de extenderse a otras especies en el futuro). Cada Tamagotchi tendrá un nombre asignado por el usuario.
Simulación de Necesidades: Cada Tamagotchi tendrá necesidades básicas simuladas, como hambre, energía y felicidad. Estos atributos varían con el tiempo y las acciones del usuario.
Interacción del Usuario: Los usuarios podrán interactuar con sus Tamagotchis realizando acciones como:
Alimentar: Reducir el nivel de hambre y aumentar ligeramente la energía y la felicidad. Los alimentos podrán ser de tres tipos específicos, se deja en libertad al programador para que defina estos alimentos. Sin embargo, cada alimento aporta diversos niveles de energía dependiendo del tipo de tamagotchi que es.
Jugar: Aumentar la felicidad y disminuir la energía, con comportamientos de juego específicos para cada tipo de Tamagotchi. Determinar tres tipo de juego, que al igual que el alimento, aporta diferentes niveles de felicidad y disminución de energía dependiendo del tipo de tamagotchi
Dormir: Restaurar la energía del Tamagotchi.
Estados de Ánimo: El estado general del Tamagotchi se refleja a través de mensajes, indicando si se siente bien, necesita atención o está mal, basándose en sus niveles de necesidad.
Comportamientos Específicos: Cada tipo de Tamagotchi exhibirá comportamientos únicos:
Los perros podrán ladrar y disfrutar de perseguir su cola.
Los gatos podrán maullar y ronronear, y tendrán una forma particular de dormir.
Las muñecas pueden decir que están aburridas o felices, además de que lloran.
Los cuyos hacen cuy cuy y les gusta roer la madera.
Visualización del Estado: Los usuarios podrán ver el estado actual de sus Tamagotchis, incluyendo sus niveles de hambre, energía, felicidad y si están durmiendo, así como características específicas de cada tipo (nivel de ladridos, si ronronea).
Herencia e Interfaces: El proyecto utiliza una clase base abstracta Tamagotchi para definir las características y comportamientos comunes a todas las mascotas virtuales. Las clases concretas como Perro,  Gato, Muñeca y Cuyo heredan de esta clase base y extienden su funcionalidad con comportamientos específicos. La interfaz TamagotchiActions define un contrato para las acciones básicas que cualquier Tamagotchi debe implementar.
Puntos Extra.
Hacer una GUI para el programa que utilicen JavaFX.

