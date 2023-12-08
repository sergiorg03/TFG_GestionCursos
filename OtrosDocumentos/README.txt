DOCUMENTO DE COFIGURACIÓN PARA EL CORRECTO FUNCIONAMIENTO DE LA APP "STUDYHUB".

COFIGURACIÓN DEL SERVIDOR XAMPP:

En primer lugar se deberá instalar XAMPP con los módulos de MySQL y phpMyAdmin como requisito imprescindible.

Seguidamente se deberá ejecutar XAMPP como administrador.

Una vez abierto se modificaran dos archivos.

- Fichero httpd.conf:
	ruta--> "C:\xampp\apache\conf\httpd.conf"
	1. Dentro de la etiqueta "<IfModule alias_module>" añaderemos el siguiente código modificando las rutas hacia la carpeta en la que hemos guardado el proyecto. La ruta deberá quedar de esta manera 
	   "C:\Users\nombreDeUsuario\...\...\...\...\nombreCarpetaEnLaQueSeHaGuardadoElProyecto\TFG_GestionCursos". En el siguiente código debemos cambiar "ruta" por la ruta anteriormente indicada.

		# 2023-12-11. Sergio. He añadido este Alias para la realizacion del proyecto de fin de grado.
    		Alias /tfg "ruta"
    		<Directory "ruta">
    			Options Indexes FollowSymLinks Includes ExecCGI
    			AllowOverride All
    			Require all granted
    		</Directory>

- Fichero config.inc.php:
	ruta--> "C:\xampp\phpMyAdmin\config.inc.php"
	1. En este fichero se deberan cambiar las sentencias 'auth_type' y 'user'. En la sentencia 'auth_type' se deberá modiicar el valor de 'config' a 'cookie', así como en la sentencia 'user' se deberá modiicar el valor de 'root' a '', 
           es decir, valor vacio. 
	   Por lo que deberá quedar de esta manera: 

	   $cfg['Servers'][$i]['auth_type'] = 'cookie';
	   $cfg['Servers'][$i]['user'] = '';

Una vez completada la configuración del servidor XAMPP iniciaremos los servicios de apache y mysql del panel de control de XAMPP.

Inserción de los datos en la Base de Datos:
Para este paso desde el panel de control de XAMPP se hará click en el botón "Admin" del módulo MySQL el cual nos llevará al navegador e iniciaremos sesión como 'root' (la contraseña por defecto está vacia). 
Seguidamente iremos al apartado "importar" y seleccionaremos los archivos SQL de la carpeta "script" del proyecto. 

El orden de importación de datos es: 
	1 --> DDL.sql
	2 --> DML.sql

Una vez completada la importación de datos se deberá obtener la IP del propio ordenador que se utilizará como servidor, es decir, el ordenador en el que se instaló XAMPP y se deberá cambiar el valor de la variable IP creada en el archivo FuncionesVarias.java por el valor de la IP del ordenador.
Deberá quedar de esta manera: this.IP = "192.168.0.1";

Una vez completados estos pasos se podrá disfrutar de la Aplicación funcionando correctamente.