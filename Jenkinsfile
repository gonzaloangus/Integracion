pipeline {
    agent any

    stages {
        stage('Etapa 1: Preparación del Proyecto') {
            steps {
                echo 'Iniciando la preparación del proyecto...'
                git ' https://github.com/gonzaloangus/Integracion.git'
            }
        }

        stage('Etapa 2: Construcción del Proyecto') {
            steps {
                echo 'Iniciando la construcción del proyecto...'
                sh 'mvn clean install'
            }
        }

        stage('Etapa 3: Reporte mediante Junit') {
            steps {
                echo 'Generando reporte mediante JUnit...'
                sh 'mvn test'
            }
        }
    }

    post {
        success {
            echo 'Todas las etapas se completaron con éxito.'

        }
        failure {
            echo 'Hubo fallos en alguna etapa. Revisar los detalles.'

        }
    }

    options {
        // Programación para ejecutar después de las 17:00
        timestamps()
        cron('0 17 * * *')
    }
}
