package hk.edu.cityu.cs.FYP.AIRegistry.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import hk.edu.cityu.cs.FYP.AIRegistry.Exception.StorageFolderCannotAccessException;
import hk.edu.cityu.cs.FYP.AIRegistry.dao.AttachmentDao;
import hk.edu.cityu.cs.FYP.AIRegistry.model.AttachmentDownload;
import hk.edu.cityu.cs.FYP.AIRegistry.model.AttachmentUpload;

@Service
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    AttachmentDao attachmentDao;

    @Value("${airegistry.storage.directory}")
    String storageBaseDirectory;

    @PostConstruct
    private void checkFolder() {
        var baseDirectory = new File(storageBaseDirectory);

        if (!baseDirectory.exists()) {
            if (!baseDirectory.mkdirs()) {
                throw new StorageFolderCannotAccessException("Cannot make new folder on" + storageBaseDirectory);
            }
        } else if (!baseDirectory.isDirectory()) {
            throw new StorageFolderCannotAccessException("The specified path is a file");
        }

        if (!baseDirectory.canRead() || !baseDirectory.canWrite()) {
            throw new StorageFolderCannotAccessException("Cannot read or write from the storage folder");
        }

    }

    @Override
    public List<Integer> getProjectAttachment(int projectId) {
        return attachmentDao.getProjectAttachments(projectId);
    }

    @Override
    public List<Integer> getDetailAttachment(int detailId) {

        return attachmentDao.getDetailAttachments(detailId);
    }

    private String saveFileToFolder(MultipartFile multipartFile) throws IOException {
        var filename = UUID.randomUUID();
        var file = new File(storageBaseDirectory + File.separator + filename.toString());

        while (file.exists()) {
            filename = UUID.randomUUID();
            file = new File(storageBaseDirectory + File.separator + filename.toString());
        }

        multipartFile.transferTo(file);

        return filename.toString();

    }

    @Override
    public int addProjectAttachment(AttachmentUpload attachmentUpload) throws IOException {

        var filename = saveFileToFolder(attachmentUpload.getMultipartFile());
        attachmentUpload.setFilename(filename);
        attachmentDao.addProjectAttachment(attachmentUpload);
        return attachmentUpload.getAttachmentId();
    }

    @Override
    public int addDetailAttachment(AttachmentUpload attachmentUpload) throws IOException {
        var filename = saveFileToFolder(attachmentUpload.getMultipartFile());
        attachmentUpload.setFilename(filename);
        attachmentDao.addDetailAttachment(attachmentUpload);
        return attachmentUpload.getAttachmentId();
    }

    @Override
    public AttachmentDownload getAttachment(int attachmentId) {
        var attachmentDownload = attachmentDao.getAttachment(attachmentId);

        var filename = attachmentDownload.getFileName();
        attachmentDownload.setFile(new File(storageBaseDirectory, filename));
        return attachmentDownload;

    }

    public void deleteAttachment(int attachmentId){
        attachmentDao.deleteAttachment(attachmentId);
    }

}
