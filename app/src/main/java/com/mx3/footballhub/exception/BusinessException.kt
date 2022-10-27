package com.mx3.footballhub.exception

import com.mx3.footballhub.ui.common.CustomMessage

class BusinessException(val businessMessage: CustomMessage) : Exception()