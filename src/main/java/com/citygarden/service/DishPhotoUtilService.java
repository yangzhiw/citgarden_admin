package com.citygarden.service;

import com.citygarden.repository.DishRepository;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.xml.bind.DatatypeConverter;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by Administrator on 2016/2/24 0024.
 */
@Service
public class DishPhotoUtilService {
    private final Logger log = LoggerFactory.getLogger(DishPhotoUtilService.class);

    @Inject
    private Environment env;

    @Inject
    private MongoDbFactory mongoDbFactory;

    @Inject
    private DishRepository dishRepository;

    public String getDishPhoto(String name) throws  Exception{
        Mongo mongo = mongoDbFactory.getDb().getMongo();
        DB db = mongo.getDB(env.getProperty("spring.data.mongodb.database"));
        log.debug("REST request to get dishPhoto");;
        GridFS gfsPhoto = new GridFS(db, "T_DISH_PHOTO");
        GridFSDBFile imageForOutput = gfsPhoto.findOne(name);
        System.out.println(imageForOutput);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        if(imageForOutput != null){
            imageForOutput.writeTo(bos);
        }
        return DatatypeConverter.printBase64Binary(bos.toByteArray());
    }

    /**
     * 给菜品增加图片
     * @param is
     * @param did
     */
    public void importPhoto(InputStream is, String did) {
        Mongo mongo = mongoDbFactory.getDb().getMongo();
        DB db = mongo.getDB(env.getProperty("spring.data.mongodb.database"));
        log.debug("REST request to post dishPhoto");
        GridFS gfsPhoto = new GridFS(db, "T_DISH_PHOTO");

        String pname = dishRepository.findOne(did)!= null ? dishRepository.findOne(did).getName() : null;

        // get image file from local drive
        GridFSInputFile gfsFile = gfsPhoto.createFile(is);

        // set a new filename for identify purpose
        gfsFile.setFilename(pname);

        // save the image file into mongoDB
        gfsFile.save();

        DBCursor cursor = gfsPhoto.getFileList();
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
    }
}
