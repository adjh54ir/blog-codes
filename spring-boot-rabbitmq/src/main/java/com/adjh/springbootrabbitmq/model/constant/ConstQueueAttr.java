package com.adjh.springbootrabbitmq.model.constant;

/**
 * 큐의 속성 키 값을 관리하는 상수 클래스입니다.
 *
 * @author : lee
 * @fileName : ConstQueueAttr
 * @since : 24. 6. 10.
 */
public class ConstQueueAttr {
    public static final String QUEUE_LAZY = "x-lazy";                                           // 브로커에서 RAM 사용량을 줄이기 위해 가능한 한 많은 메시지를 디스크에 유지하는 lazy 모드로 큐를 설정합니다.
    public static final String QUEUE_TYPE = "x-queue-type";                                     // 'classic', 'quorum', 'stream' 중 하나의 큐 유형을 선언하기 위해 큐 인수를 설정합니다.
    public static final String QUEUE_TTL = "x-message-ttl";                                     // 메시지의 생존 시간을 설정합니다.
    public static final String QUEUE_EXPIRES = "x-expires";                                     // 큐가 삭제되기 전에 사용되지 않을 수 있는 시간을 설정합니다.
    public static final String QUEUE_OVERFLOW = "x-overflow";                                   // 최대 메시지 수 또는 최대 메시지 크기가 초과되어 메시지가 삭제될 때의 오버플로 모드를 설정합니다.
    public static final String QUEUE_PRIORITY = "x-max-priority";                               // 큐가 지원할 메시지의 우선 순위 수의 최대값을 설정합니다.
    public static final String QUEUE_MAX_LENGTH = "x-max-length";                               // 큐에서 허용하는 (준비된) 메시지의 수를 설정합니다.
    public static final String QUEUE_MAX_LENGTH_BYTE = "x-max-length-bytes";                    // 큐에서 허용하는 총 메시지 바디 크기를 설정합니다. 이 크기를 초과하면 큐는 메시지를 삭제하기 시작합니다.
    public static final String QUEUE_MASTER_LOCATOR = "x-queue-master-locator";                 // 노드 클러스터에 큐 마스터가 위치할 노드를 결정하는 마스터 로케이터 모드를 설정합니다.
    public static final String QUEUE_DEAD_LETTER_EXCHANGE = "x-dead-letter-exchange";           // 만료 또는 거부된 메시지를 라우팅할 dead-letter exchange를 설정합니다.
    public static final String QUEUE_DEAD_LETTER_ROUTE_KEY = "x-dead-letter-routing-key";       // 만료 또는 거부된 메시지를 dead-letter exchange로 라우팅할 때 사용할 라우팅 키를 설정합니다.
    public static final String QUEUE_SINGLE_ACTIVE_CONSUMER = "x-single-active-consumer";       // 'x-single-active-consumer' 큐 인수를 설정합니다. 이는 한 번에 하나의 소비자만 큐에서 메시지를 소비하도록 합니다.
}
