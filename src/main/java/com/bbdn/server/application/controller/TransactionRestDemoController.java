package com.bbdn.server.application.controller;

import com.bbdn.server.domain.entity.UserEntity;
import com.bbdn.server.domain.interfaces.request.CreateUserInfoRequest;
import com.bbdn.server.service.TransactionDemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// @Controller : view return
@RestController // http header + body return
@Slf4j
@RequestMapping("/users") // http://localhost:8080/users
public class TransactionRestDemoController {

    @Autowired
    private TransactionDemoService transactionDemoService;

    /*
    * http method
    * post : create or insert - body data // id : 2, name = test, email = test@example.com, password = 1111
    * get : select - queryString
    *
    * put : update - body data // 통째로 업데이트 할 때 name = test2, email = test2@example.com, password = 2222
    * patch : update - body data // 일부 값만 업데이트 할 때 password = 2222
    *
    * delete : delete - queryString
    *
    * 1) queryString vs body data?
    * 2) put vs patch
    *
    * ============================
    * front -> back : GET http://localhost:8080/users/1
    * back -> front : users/1 데이터를 db, session 등 에서 1번에 해당하는 데이터를 찾아서 return
    * ============================
    * front -> back : POST http://localhost:8080/users + (body) id : 2, name = test, email = test@example.com ...
    * back -> front : http body 에 넘어온 데이터를 이용해 create or insert 처리 후 결과값 리턴
    * */

    // http://localhost:8080/users

    @PostMapping("")
    public ResponseEntity saveNewUserInfo(@RequestBody CreateUserInfoRequest createUserInfoRequest) {

        log.info("createUserInfoRequestVO : " + createUserInfoRequest.toString());

        UserEntity userEntity = transactionDemoService.saveNewUserInfoWithDefault(createUserInfoRequest);

        log.info("saveNewUserInfoWithDefault : " + userEntity);

        return ResponseEntity.ok(userEntity);
    }

    // ?key=value&key=value
    // ?id=1
    // localhost:8080?id=1&nickName=test
    // localhost:8080?id=1 (호출 불가, nickName 필요)
    @GetMapping("")
    public ResponseEntity getUserInfo(@RequestParam String email) throws Exception {

        log.info("getUserInfo email : " + email);

        UserEntity userEntity = transactionDemoService.getUserInfoById(email);

        log.info("getUserInfo : " + userEntity);

        return ResponseEntity.ok(userEntity);
    }


    @PutMapping("/")
    public ResponseEntity putUserInfo(@RequestBody CreateUserInfoRequest createUserInfoRequest) {

        log.info("putUserInfo : " + createUserInfoRequest.toString());

        UserEntity userEntity = transactionDemoService.putUserInfo(createUserInfoRequest);

        log.info("putUserInfo : " + userEntity.toString());

        return ResponseEntity.ok(userEntity);
    }


    @DeleteMapping("/")
    public ResponseEntity deleteUserInfo(@RequestParam String email) {

        log.info("getUserInfo email : " + email);

        int delResult = transactionDemoService.deleteUserInfoById(email);

        log.info("deleteUserInfo : " + delResult);

        return ResponseEntity.ok(delResult);
    }


    @PostMapping("/failure")
    public ResponseEntity saveNewUserInfoWithReadOnly(@RequestBody CreateUserInfoRequest createUserInfoRequest) {

        UserEntity userEntity = transactionDemoService.saveNewUserInfoWithReadOnlyTrue(createUserInfoRequest);

        log.info("saveNewUserInfoWithReadOnly : " + userEntity.toString());

        return ResponseEntity.ok(userEntity);
    }

}
