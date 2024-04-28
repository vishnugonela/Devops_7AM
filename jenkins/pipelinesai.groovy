pipeline {
    agent any

    stages {
        stage('Connect to MySQL') {
            steps {
                script {
                    // MySQL connection details
                    def mysqlHost = 'your_mysql_host'
                    def mysqlUser = 'your_mysql_username'
                    def mysqlPassword = 'your_mysql_password'
                    def mysqlDatabase = 'your_mysql_database'

                    // Connect to MySQL
                    def connection = connectToMySQL(mysqlHost, mysqlUser, mysqlPassword, mysqlDatabase)
                    if (connection) {
                        // Retrieve master-slave details
                        def masterSlaveDetails = getMasterSlaveDetails(connection)
                        if (masterSlaveDetails) {
                            echo "Master-Slave Details:"
                            echo masterSlaveDetails
                        } else {
                            echo "No master-slave details found."
                        }
                        connection.close()
                    } else {
                        error "Could not establish MySQL connection."
                    }
                }
            }
        }
    }
}

def connectToMySQL(host, user, password, database) {
    def connection
    try {
        connection = new Sql.newInstance("jdbc:mysql://${host}/${database}", user, password, 'com.mysql.jdbc.Driver')
    } catch (Exception e) {
        error "Error connecting to MySQL: ${e}"
    }
    return connection
}

def getMasterSlaveDetails(connection) {
    def query = "SHOW SLAVE STATUS"
    def resultSet = connection.rows(query)
    if (resultSet) {
        return resultSet
    } else {
        return null
    }
}
