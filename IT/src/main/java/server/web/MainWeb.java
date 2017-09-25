package server.web;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import db.DB;
import db.table.Storage;
import db.table.Table;
import db.table.Table.TableBuilder;
import db.table.field.base.BaseField;
import db.table.field.base.BaseFieldType;
import server.service.DbService;
import server.service.TableService;

@RestController
public class MainWeb {

	
    private static final String template = "Hello, %s!";
//    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private DbService dbService;
    
    @Autowired
    private Storage storage;
    
    @Autowired
    private TableService tableService;
    
    @RequestMapping("/greeting")
    public String greeting() throws Exception {
    	//Storage storage = dbService.getStorage();
    	DB db1 = new DB("1");
    	DB db2 = new DB("2");
    	Table testTable = Table.tableBuilder().setTableName("TestTable")
				.addTableBaseField(new BaseField("field1", BaseFieldType.INTEGER))
				.addTableBaseField(new BaseField("field2", BaseFieldType.REAL)).build();
    	//dbService.createDB(storage, db1);
    	//dbService.createDB(storage, db2);
    	
    	dbService.createDB(db1);
    	dbService.createDB(db2);

    	tableService.addTable(db1, testTable);
    	
    	//System.out.println(db.getDbName());
    	String ss = "";
    	for(DB dd : storage.getDbs()){
    		ss += dd.toString();
    	}
        return ss;
    }
}




