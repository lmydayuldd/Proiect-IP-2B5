/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomExceptions;

/**
 *
 * @author Procop Vladimir
 */
public class DataNotValidException extends Exception{
    public DataNotValidException(String message){
        super(message);
    }
}

