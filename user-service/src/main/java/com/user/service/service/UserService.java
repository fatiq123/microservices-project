package com.user.service.service;

import com.user.service.dto.InvoiceDTO;
import com.user.service.dto.UserInfo;
import com.user.service.entities.UserE;
import com.user.service.exception.UserException;
import com.user.service.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final InvoiceService invoiceService;

    public List<UserInfo> getUsers(List<String> ids) {

        if (CollectionUtils.isEmpty(ids)) {
            throw new UserException("Bad Request", List.of("User ids are missing in request"), HttpStatus.BAD_REQUEST);
        }

        List<UserE> userList = userRepository.findAllById(ids);

        return userList.stream()
                .map(this::getUserInfo)
                .toList();
    }


    private UserInfo getUserInfo(UserE user) {

        List<InvoiceDTO> invoiceDTOS = invoiceService.callInvoiceServiceAndGetInvoiceDTOList(user.getId());

        UserInfo userInfo = new UserInfo();
        userInfo.setId(user.getId());
        userInfo.setName(user.getName());
        userInfo.setAge(user.getAge());
        userInfo.setGender(user.getGender());
        userInfo.setCreatedTime(user.getCreatedTime());
        userInfo.setUpdatedTime(user.getUpdatedTime());

        userInfo.setInvoiceDTOList(invoiceDTOS);

        return userInfo;
    }
    

}
