package server.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import db.DB;
import db.table.Storage;
import server.service.DbService;

@RestController
// @RequestMapping("/db")
public class DbController {

    @Autowired
    private DbService dbService;

    @RequestMapping(method = RequestMethod.GET, value = "/db")
    public String all() throws Exception {
	System.out.println("GET /db/ all");
	StringBuilder stringBuilder = new StringBuilder();
	dbService.getDbs().stream()
		.forEach(db -> stringBuilder.append(db.toString()));
	return stringBuilder.toString();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/db")
    public ResponseEntity createDb(@RequestParam String dbName)
	    throws Exception {
	System.out.println("POST /db/ createDb " + dbName);
	DB db = new DB(dbName);
	Boolean result = dbService.createDB(db);
	if (result == true) {
	    return new ResponseEntity<>(HttpStatus.OK);
	}
	return new ResponseEntity<String>("Db " + dbName + " already exists",
		HttpStatus.CONFLICT);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/db")
    public ResponseEntity deleteDb(@RequestParam String dbName)
	    throws Exception {
	System.out.println("DELETE /db/ deleteDb " + dbName);
	Boolean result = dbService.deleteDB(dbName);
	if (result == true) {
	    return new ResponseEntity<>(HttpStatus.OK);
	}
	return new ResponseEntity<String>("Db " + dbName + " does not exist",
		HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/db/{dbName}")
    public ResponseEntity getDb(@PathVariable(value = "dbName") String dbName)
	    throws Exception {
	System.out.println("GET /db/" + dbName + " getDb " + dbName);
	DB result = dbService.getDB(dbName);
	if (result != null) {
	    return new ResponseEntity<DB>(result, HttpStatus.OK);
	}
	return new ResponseEntity<String>("Db " + dbName + " does not exist",
		HttpStatus.BAD_REQUEST);
    }
}
