package com.actionbarsherlock.plugin;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import com.actionbarsherlock.tools.DAOGenerager;
import com.actionbarsherlock.tools.DaoMetaData;
import com.actionbarsherlock.tools.DbUtil;

/**
 * @goal compile
 * @phase compile
 */
public class GeneragerMojo extends AbstractMojo {
    /**
     * @parameter expression="${codeTemplate}"
     *            default-value="/com/actionbarsherlock/tools/codeTemplate"
     */
    public String codeTemplate;
    /**
     * @parameter expression="${outPutFile}"
     *            default-value="./outPutFile/"
     */
    public String outPutFile;

    /**
     * @parameter expression="${codeISDAOName}"
     *            default-value="codeISDAOName.html"
     */
    public String codeISDAOName;
    /**
     * @parameter expression="${codeSDAOName}" default-value="codeSDAOName.html"
     */
    public String codeSDAOName;
    /**
     * @parameter expression="${codeSVOName}" default-value="codeSVOName.html"
     */
    public String codeSVOName;
    /**
     * @parameter expression="${codeModel}" default-value="book"
     */
    public String codeModel;

    /**
     * @parameter expression="${codeTableName}"
     *            default-value="book,chapter,clear,site_pattern,siteInfo,upgrade"
     */
    public String codeTableName;

    public void execute() throws MojoExecutionException {
        getLog().info("generate Template start \n ");
        String[] arg = codeTableName.split(",");
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();// DBHelper.getConnection();
            for (int i = 0; i < arg.length; i++) {
                DatabaseMetaData dbMeta = conn.getMetaData();
                DaoMetaData daoMeta = new DaoMetaData(dbMeta, codeModel, arg[i]);
                DAOGenerager gen = new DAOGenerager(codeTemplate, outPutFile,
                        codeISDAOName, codeSDAOName, codeSVOName, codeModel,
                        codeTableName);
                gen.generateSVOFile(daoMeta);
                gen.generateSDAOImplFile(daoMeta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(conn);
        }
        getLog().info("generate Template end \n ");
    }
}
