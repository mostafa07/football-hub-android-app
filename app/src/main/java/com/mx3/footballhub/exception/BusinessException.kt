package com.mx3.footballhub.exception

import com.mx3.footballhub.data.model.app.CustomMessage

class BusinessException(val businessMessage: CustomMessage) : Exception()