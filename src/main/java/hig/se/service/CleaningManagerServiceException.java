package hig.se.service;

public class CleaningManagerServiceException extends RuntimeException {
        public CleaningManagerServiceException(){

        }
        public CleaningManagerServiceException(String msg){
            super(msg);
        }
        public CleaningManagerServiceException(String msg, Throwable cause){
            super(msg, cause);
        }
        public CleaningManagerServiceException(Throwable cause){
            super(cause);
        }
}
