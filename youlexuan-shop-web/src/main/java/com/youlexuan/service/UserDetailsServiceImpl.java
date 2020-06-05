package com.youlexuan.service;

import java.util.ArrayList;
import java.util.List;
import com.wd.pojo.TbSeller;
import com.wd.sellergoods.service.SellerService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

//认证类

public class UserDetailsServiceImpl implements UserDetailsService {
    private SellerService sellerService; //远程调用商家商品服务
    public void setSellerService(SellerService sellerService) {
        this.sellerService = sellerService;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("经过了UserDetailsServiceImpl");

        List<GrantedAuthority> grantAuths=new ArrayList();
        grantAuths.add(new SimpleGrantedAuthority("ROLE_SELLER"));

//得到商家对象，这个username就是商家表中的seller_id
        TbSeller seller = sellerService.findOne(username);
        if(seller!=null){
            if(seller.getStatus().equals("1")){  //商家状态为1代表已审核
                return new User(username,seller.getPassword(),grantAuths);
            }else{
                return null;
            }
        }else{
            return null;
        }
    }
}