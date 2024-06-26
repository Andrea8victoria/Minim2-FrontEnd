# Implementación individual en el proyecto

Nueva funcionalidad que permita realizar una consulta sobre la aplicación.

Operaciones:
- En el FrontEnd: añadir una nueva actividad (activity_question.xml) a la aplicación Android que proporcione un pequeño formulario para solicitar y enviar información sobre un tema.
- En el BackEnd: nueva ruta (/question) que reciba la nueva consulta.

Estructura:
  POST /question
  {
    date: ‘ ’,
    title: ‘ ’,
    message: ‘ ’,
    sender: ‘ ’
  }
