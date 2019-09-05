package com.n8ify.inheritor

import com.n8ify.inheritor.constant.LogLevel
import com.n8ify.inheritor.dao.UserDao
import com.n8ify.inheritor.dao.impl.UserDaoImpl
import com.n8ify.inheritor.service.LoggerService
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class InheritorApplicationTests {

    val TAG = InheritorApplicationTests::class.java.simpleName

    @Autowired
    lateinit var userDao: UserDaoImpl

    @Autowired
    lateinit var logger : LoggerService

	@Test
	fun contextLoads() {
        logger.systemLogger(TAG, "Result", userDao.findUserByUsername("shuz1ify"), LogLevel.INFO)
	}

}
