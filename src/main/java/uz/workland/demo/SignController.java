package uz.workland.demo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.workland.demo.model.ParentUserEntity;
import uz.workland.demo.model.ResponseData;
import uz.workland.demo.model.SignEntity;

import java.util.UUID;

@RestController
@RequestMapping("sign")
public class SignController {
    @PostMapping("up")
    public ResponseData signUp(@RequestBody SignEntity signEntity){
        String emailRegEx = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        String phoneRegEx = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$";
        if (signEntity.getUserName().matches(emailRegEx)){
            signEntity.setEmail(signEntity.getUserName());
        }else if (signEntity.getUserName().matches(phoneRegEx)){
            signEntity.setPhone(signEntity.getUserName());
        }else {
            return new ResponseData(1,"userName is incorrect",null);
        }
        return new ResponseData(0,"",new ParentUserEntity(UUID.randomUUID()));
    }

    @PostMapping("in")
    public ResponseData signIn(@RequestBody SignEntity signEntity){
        return signUp(signEntity);
    }
}
