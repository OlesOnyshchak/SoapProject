package com.softserve.soap.config;



    import javax.persistence.EntityManager;
    import javax.persistence.EntityManagerFactory;
    import javax.persistence.Persistence;

    public class EntityManagerConfiguration
    {
        private static EntityManagerFactory entityManagerFactory = buildEntityManagerFactory();
        private EntityManager entityManager = entityManagerFactory.createEntityManager();

        private static EntityManagerFactory buildEntityManagerFactory()
        {
            try
            {
                loadDriver();
                return Persistence.createEntityManagerFactory("MainUnit");
            }
            catch (Throwable ex)
            {
                System.err.println("An exception while initializing occurred");
                throw new ExceptionInInitializerError(ex);
            }
        }

        public  EntityManager getEntityManager()
        {
            return entityManager;
        }

        public  void closeSession()
        {
            entityManagerFactory.close();
        }

        private static void loadDriver() throws ClassNotFoundException
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        }
    }
