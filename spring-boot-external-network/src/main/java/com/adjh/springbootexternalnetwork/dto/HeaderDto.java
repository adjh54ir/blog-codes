package com.adjh.springbootexternalnetwork.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : HeaderDto
 * @since : 11/24/24
 */
@Getter
@Setter
public class HeaderDto {
    private List<String> date;
    private List<String> contentType;
    private List<String> transferEncoding;
    private List<String> connection;
    private List<String> reportTo;
    private List<String> reportingEndpoints;
    private List<String> nel;
    private List<String> xPoweredBy;
    private List<String> xRatelimitLimit;
    private List<String> xRatelimitRemaining;
    private List<String> xRatelimitReset;
    private List<String> vary;
    private List<String> accessControlAllowCredentials;
    private List<String> cacheControl;
    private List<String> pragma;
    private List<String> expires;
    private List<String> xContentTypeOptions;
    private List<String> etag;
    private List<String> via;
    private List<String> cfCacheStatus;
    private List<String> age;
    private List<String> server;
    private List<String> cfRay;
    private List<String> altSvc;
    private List<String> serverTiming;
    @Builder
    public HeaderDto(List<String> date, List<String> contentType, List<String> transferEncoding, List<String> connection, List<String> reportTo, List<String> reportingEndpoints, List<String> nel, List<String> xPoweredBy, List<String> xRatelimitLimit, List<String> xRatelimitRemaining, List<String> xRatelimitReset, List<String> vary, List<String> accessControlAllowCredentials, List<String> cacheControl, List<String> pragma, List<String> expires, List<String> xContentTypeOptions, List<String> etag, List<String> via, List<String> cfCacheStatus, List<String> age, List<String> server, List<String> cfRay, List<String> altSvc, List<String> serverTiming) {
        this.date = date;
        this.contentType = contentType;
        this.transferEncoding = transferEncoding;
        this.connection = connection;
        this.reportTo = reportTo;
        this.reportingEndpoints = reportingEndpoints;
        this.nel = nel;
        this.xPoweredBy = xPoweredBy;
        this.xRatelimitLimit = xRatelimitLimit;
        this.xRatelimitRemaining = xRatelimitRemaining;
        this.xRatelimitReset = xRatelimitReset;
        this.vary = vary;
        this.accessControlAllowCredentials = accessControlAllowCredentials;
        this.cacheControl = cacheControl;
        this.pragma = pragma;
        this.expires = expires;
        this.xContentTypeOptions = xContentTypeOptions;
        this.etag = etag;
        this.via = via;
        this.cfCacheStatus = cfCacheStatus;
        this.age = age;
        this.server = server;
        this.cfRay = cfRay;
        this.altSvc = altSvc;
        this.serverTiming = serverTiming;
    }
}
