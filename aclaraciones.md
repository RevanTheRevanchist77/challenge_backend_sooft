# Aclaraciones y decisiones de diseño

En este archivo coloco las dudas, suposiciones y decisiones tomadas durante el desarrollo del proyecto.

Elegi JPA ya que para este challenge, es bastante mas sencillo de manejar a mi gusto para casos de prueba como este, originalmente utilice lombok pero tuve multiples inconvenientes con las dependencias y me vi obligado para no demorar mas en la resolucion a utilizar directamente getters y setters.

-------

## **1. Decisiones sobre la Arquitectura Hexagonal**


  Implemente como se sugirio la arquitectura hexagonal, ya que esta  permite desacoplar la lógica de negocio de la infraestructura (base de datos, APIs, etc), ademas de facilitar las pruebas unitarias y el mantenimiento del codigo.

	##Cómo se implemento la arquitectura hexagonal?
  - **Dominio (Core):** Tiene las entidades dentro del package models (`Empresa`, `Transferencia`) y los servicios de negocio dentro del package services(`EmpresaService`, `TransferenciaService`).

  - **Infraestructura:** Tiene los repositorios en el package repository (`EmpresaRepository`, `TransferenciaRepository`) y los controladores en el package controller (`EmpresaController`, `TransferenciaController`).
  - **API:** Los endpoints están expuestos a traves de controladores REST.


-------


## **2. Suposiciones sobre las especificaciones indicadas**

- **Endpoint 1: Empresas que hicieron transferencias el ultimo mes**
  - Se asumio que "el ultimo mes" se refiere al mes calendario anterior al mes actual. 
  - Se decidio no incluir empresas que no hayan realizado transferencias, aunque estas esten registradas.

- **Endpoint 2: Empresas que se adhirieron el ultimo mes**
  - Se asumio que "el uiltimo mes" se refiere al mes calendario anterior al mes actual, igual al endpoint anterior.
  - Se incluyen todas las empresas que tengan una fecha de adhesion dentro de ese rango.

- **Endpoint 3: Adhesion de una empresa**
  - Se valido que el CUIT sea unico para evitar duplicados.
  - Se asumio que la fecha de adhesion es la fecha actual en la que se registra la empresa.

-------

## **3. Dudas surgidas durante el desarrollo**

- **Duda 1: Validar el CUIT**
  - ¿Debe validarse el formato del CUIT (por ejemplo, que tenga 11 diigitos)? En este caso, asumi que el CUIT debe tener 11 digitos y se agrego una validacion basica en el servicio.

- **Duda 2: Manejo de fechas**
  - Debe manejarse la zona horaria para las fechas de transferencia y adhesion? Decidi utilizar `LocalDate` sin zona horaria, asumiendo que todas las fechas están en la misma zona horaria.

- **Duda 3: Paginacion en los endpoints**
  - ¿Deben los endpoints devolver todos los resultados o estar paginados? En este caso, decidi no implementar paginacion, pero deje abierta la posibilidad de agregarla en el futuro.

-------

## **4. Posibles mejoras a futuro**

- Implementar paginacion en los endpoints que devuelven listas de empresas o transferencias.
- Agregar una autenticacion y autorización para proteger los endpoints.
- Mejorar las validaciones de datos, como el formato del CUIT o que existan cuentas validas.


