package com.c1646njava.tuvivienda.services.implementation;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class CloudinaryServiceImp {

Cloudinary cloudinary;

private Map<String, String> valuesMap = new HashMap<>();

public CloudinaryServiceImp(){

    valuesMap.put("cloud_name","dprhopips");
    valuesMap.put("api_key","894311123961918");
    valuesMap.put("api_secret","SAL6phTWQHcyVFHRN7RYlnKAy70");
    cloudinary = new Cloudinary(valuesMap);
}

public Map upload(MultipartFile multipartFile) throws IOException {
    File file = conver(multipartFile);
    Map result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
    file.delete();
    return  result;
}
public Map delete(String id) throws IOException {
    Map result = cloudinary.uploader().destroy(id, ObjectUtils.emptyMap());
    return result;
}
private File conver(MultipartFile multipartFile) throws IOException {
    File file = new File(multipartFile.getOriginalFilename());
    FileOutputStream fo = new FileOutputStream(file);
    fo.write(multipartFile.getBytes());
    fo.close();
    return file;
}
}
