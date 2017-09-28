# WebApp_Ensolver
Proyecto de entrevista empresa ensolver

Inicio del proyecto acomodando usurio 


Tecnologias utilizadas:
se utilizo El lenguaje java con Spring, SpringMVC
Maven para manejo de dependencias
toncat 8.5 como servidor
hibernate como orm

el esquema es el d euna aplicacion web tradicional, y s euso angular en algunos detalles, para simplificar alunas tareas.
para la vista se utiliza Html5 css, media query, boostrap y javascript.

Deploy del sistema:
El sistema puede deployarse para testearlo clonando el repositorio, e importandolo en eclipse por ejemplo(ide de desarrollo utilizado sobre windows)
se recomienda instalar una version separada del toncat en el equipo ya que si s eprueba con la del eclipse, podria haber problemas dado que en ocasiones no genera los directorios correctamente dicho ide.

BBDD:
Se Utiliza como motor d ebase de datos mySQL y es hibernate quien genera las tablas en base a las clases del modleo.
Nota, es necesario crear un usuario para administrar dicha base de datos> user:WebAppEnsolver, password: WebAppEnsolver, dbname: WebAppEnsolver


Tareas realizadas:
Se cumplio con las actividades solicitadas en el enunciado
de realizaron los siguientes agregados:
registro de usuario
funcion de cambiar de estado una tarea al hacer click sobre el check box que la acompa;a
se intento de avanzar algo mas que lo basico en lo que era el dise;o siempre respetando los mockups, se tiene un favicon (letra E en verde inspirado en ensolver) y seintento de que todos el sistema sea medianamente webresponsibe, le faltarian limar algunas asperesas, pero es bastante adaptable
se segurizo la password de los usuarios hasheandola en la base de datos.
se agrego una funcioalidad que permite al usuario mostrar todas sus tareas de su lista o solo las tareas hin realizar.
se utilizo apenas angular y servicios rest, para al momento de editar la informaion de una tarea, conseguir su detalle, para eso se utilizo angular desde el cliente y rest en el controlador del servidor


consideraciones: se considero que una tarea se podia crear como pendiente o realizada, asi como que al pasarla a realizada, la misma no se elimine
 por este mismo motivo es que no se puso un filtro en uanto a la fecha de vencimiento.
 
 al no haber especificaciones lmitantes se tomo la vision mas amplia posible, siendo que restringir posibilidad, siempre es mas facil que generarlas.
 
 
 Para finalizar, hace falta decir que se utilizo loalDate de java8 para las fechas, y al no ser del todo compatible con las versiones de jpa, y hibenate, se debio restringir el formato de la fecha a dd/mm/aa.
 
 cualquier duda, ponerse en contacto con el autor
 
 
 Información de autor:
 Martín R. Valdez
 email: martin.r.valdez@hotmail.com
 
 





