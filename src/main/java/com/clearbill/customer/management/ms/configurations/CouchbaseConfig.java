package com.clearbill.customer.management.ms.configurations;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;

@Configuration
public class CouchbaseConfig extends AbstractCouchbaseConfiguration {

    @Autowired
    private ApplicationContext context;

    @Value("${app.couchbase.connection-string}")
    private String connectionString;

    @Value("${app.couchbase.user-name}")
    private String userName;

    @Value("${app.couchbase.password}")
    private String password;

    @Value("${app.couchbase.bucket-primary}")
    private String customerBucket;


    @Override
    public String getConnectionString() {
        return connectionString;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getBucketName() {
        return customerBucket;
    }

//    @Override
//    public void configureRepositoryOperationsMapping(RepositoryOperationsMapping mapping) {
//        try {
//            mapping.mapEntity(User.class, getCouchbaseTemplate(userBucket));
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//    private CouchbaseTemplate getCouchbaseTemplate(String bucketName) throws Exception {
//        CouchbaseTemplate template = new CouchbaseTemplate(couchbaseClientFactory(bucketName),
//                mappingCouchbaseConverter(couchbaseMappingContext(customConversions()),
//                        new CouchbaseCustomConversions(Collections.emptyList())));
//
//        template.setApplicationContext(context);
//        return template;
//    }
//
//    private CouchbaseClientFactory couchbaseClientFactory(String bucketName) {
//        return new SimpleCouchbaseClientFactory(couchbaseCluster(couchbaseClusterEnvironment()),
//                bucketName, this.getScopeName());
//    }
}
