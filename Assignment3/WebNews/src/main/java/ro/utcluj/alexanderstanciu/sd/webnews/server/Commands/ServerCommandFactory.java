/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.alexanderstanciu.sd.webnews.server.Commands;

/**
 *
 * @author XtraSonic
 */
public class ServerCommandFactory {

    public static Command getCommand(String[] args)
    {
        switch(args[0])
        {
            case "getArticles":
                return new GetAriclesCommand();
            case "getWriters":
                return new GetWritersCommand();
            case "deleteWriter":
                return new DeleteWriterCommand(args[1]);
            case "updateWriter":
                return new UpdateWriterCommand(args[1], args[2], args[3]);
            case "login":
                return new LoginCommand(args[1], args[2]);
            case "addWriter":
                return new AddWriterCommand(args[1], args[2]);
            case "writeArticle":
                return new WriteArticleCommand(args[1], 
                        args[2],
                        args[3],
                        args[4],
                        args[5],
                        args[6]
                );
        }
            

        return null;
    }

}
