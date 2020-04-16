package com.dmytryk.crud.repository;

    import com.dmytryk.crud.entry.User;
    import lombok.extern.slf4j.Slf4j;
    import org.junit.Test;
    import org.junit.runner.RunWith;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
    import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@DataMongoTest
@RunWith(SpringRunner.class)
public class UserRepositoryIT {

  @Autowired
  private UserRepository userRepository;

  @Test
  public void save_and_find_all_users() {
    User user = userRepository.save(User.builder().email("save@gmail.com").build());
    log.info(userRepository.findAll().toString());
  }

  @Test
  public void post_and_find_user_by_id() {
    User user = userRepository.insert(User.builder().age(20).build());
    log.info(userRepository.findById(user.getUserId()).toString());
  }

  @Test
  public void delete_and_find_user_by_id() {
    User user = userRepository.insert(User.builder().email("dmytro@gmail.com").build());
    log.info(userRepository.findById(user.getUserId()).toString());
    userRepository.deleteById(user.getUserId());
    log.info(userRepository.findById(user.getUserId()).toString());
  }

}
