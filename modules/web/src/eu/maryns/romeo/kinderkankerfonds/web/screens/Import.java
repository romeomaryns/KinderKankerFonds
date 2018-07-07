package eu.maryns.romeo.kinderkankerfonds.web.screens;

import com.haulmont.cuba.core.global.FileStorageException;
import com.haulmont.cuba.gui.backgroundwork.BackgroundWorkWindow;
import com.haulmont.cuba.gui.components.AbstractWindow;
import com.haulmont.cuba.gui.components.FileUploadField;
import com.haulmont.cuba.gui.executors.BackgroundTask;
import com.haulmont.cuba.gui.executors.TaskLifeCycle;
import com.haulmont.cuba.gui.upload.FileUploadingAPI;
import eu.maryns.romeo.kinderkankerfonds.service.ImportService;

import javax.inject.Inject;
import java.io.File;
import java.util.Map;
import java.util.UUID;

public class Import extends AbstractWindow {

    @Inject
    private FileUploadField uploadField;

    @Inject
    private FileUploadField uploadAdres;

    @Inject
    private FileUploadField kkfmail;

    @Inject
    private FileUploadField adresELField;

    @Inject
    private FileUploadField tmppatField;

    @Inject
    private FileUploadingAPI fileUploadingAPI;

    @Inject
    private ImportService importService;

    @Override
    public void init(Map<String, Object> params) {
        uploadField.addFileUploadSucceedListener(e -> {
            UUID fileId = uploadField.getFileId();
            File file = fileUploadingAPI.getFile(fileId);

            processFile(file);

            try {
                fileUploadingAPI.deleteFile(fileId);
            } catch (FileStorageException ex) {
                throw new RuntimeException(ex);
            }
        });

        uploadAdres.addFileUploadSucceedListener(e -> {
            UUID fileId = uploadAdres.getFileId();
            File file = fileUploadingAPI.getFile(fileId);

            processAdres(file);

            try {
                fileUploadingAPI.deleteFile(fileId);
            } catch (FileStorageException ex) {
                throw new RuntimeException(ex);
            }
        });

        kkfmail.addFileUploadSucceedListener(e -> {
            UUID fileId = kkfmail.getFileId();
            File file = fileUploadingAPI.getFile(fileId);

            processKkfMail(file);

            try {
                fileUploadingAPI.deleteFile(fileId);
            } catch (FileStorageException ex) {
                throw new RuntimeException(ex);
            }
        });
        adresELField.addFileUploadSucceedListener(e -> {
            UUID fileId = adresELField.getFileId();
            File file = fileUploadingAPI.getFile(fileId);

            processAdresELField(file);

            try {
                fileUploadingAPI.deleteFile(fileId);
            } catch (FileStorageException ex) {
                throw new RuntimeException(ex);
            }
        });
        tmppatField.addFileUploadSucceedListener(e -> {
            UUID fileId = tmppatField.getFileId();
            File file = fileUploadingAPI.getFile(fileId);

            processTmpPat(file);

            try {
                fileUploadingAPI.deleteFile(fileId);
            } catch (FileStorageException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void processFile(File file) {
        BackgroundWorkWindow.show(new BackgroundTask<Void, Void>(1200, this) {
            @Override
            public Void run(TaskLifeCycle<Void> taskLifeCycle) throws Exception {
                importService.importCSV(file);
                return null;
            }
        }, "windowTitle", "windowMessage", true);
    }


    private void processAdres(File file) {
        BackgroundWorkWindow.show(new BackgroundTask<Void, Void>(1200, this) {
            @Override
            public Void run(TaskLifeCycle<Void> taskLifeCycle) throws Exception {
                importService.importAdres(file);
                return null;
            }
        }, "windowTitle", "windowMessage", true);
    }

    private void processKkfMail(File file) {
        BackgroundWorkWindow.show(new BackgroundTask<Void, Void>(1200, this) {
            @Override
            public Void run(TaskLifeCycle<Void> taskLifeCycle) throws Exception {
                importService.importKkfMail(file);
                return null;
            }
        }, "windowTitle", "windowMessage", true);
    }

    private void processAdresELField(File file) {
        BackgroundWorkWindow.show(new BackgroundTask<Void, Void>(1200, this) {
            @Override
            public Void run(TaskLifeCycle<Void> taskLifeCycle) throws Exception {
                importService.importAdresEL(file);
                return null;
            }
        }, "windowTitle", "windowMessage", true);
    }

    private void processTmpPat(File file) {
        BackgroundWorkWindow.show(new BackgroundTask<Void, Void>(1200, this) {
            @Override
            public Void run(TaskLifeCycle<Void> taskLifeCycle) throws Exception {
                importService.importTmpPat(file);
                return null;
            }
        }, "windowTitle", "windowMessage", true);
    }
}