package com.loja.loja.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class FirebaseStorageService {

    private Storage storage;

    @Autowired
    private Environment environment;

    @PostConstruct
    public void initialize() throws IOException {
        String firebaseConfigPath = environment.getProperty("firebase.config.path");

        try (InputStream serviceAccount = new FileInputStream(firebaseConfigPath)) {
            GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);

            storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        }
    }

    public Storage getStorage() {
        return storage;
    }

    public String getBucketName() {
        return environment.getProperty("firebase.storageBucket");
    }

    public String uploadImageToFirebaseStorage(byte[] fileBytes, String fileName, String contentType) {
        BlobId blobId = BlobId.of(getBucketName(), fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(contentType).build();
        Blob blob = storage.create(blobInfo, fileBytes);

        return blob.getMediaLink();
    }
}
