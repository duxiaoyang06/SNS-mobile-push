/*
 * Copyright 2014 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 * http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.MessageAttributeValue;
//import AmazonSNSClientWrapper;
//import SampleMessageGenerator.Platform;

public class SNSMobilePush {

    private AmazonSNSClientWrapper snsClientWrapper;

    public SNSMobilePush(AmazonSNS snsClient) {
        this.snsClientWrapper = new AmazonSNSClientWrapper(snsClient);
    }

    public static final Map<SampleMessageGenerator.Platform, Map<String, MessageAttributeValue>> attributesMap = new HashMap<SampleMessageGenerator.Platform, Map<String, MessageAttributeValue>>();
    static {
        attributesMap.put(SampleMessageGenerator.Platform.ADM, null);
        attributesMap.put(SampleMessageGenerator.Platform.GCM, null);
        attributesMap.put(SampleMessageGenerator.Platform.APNS, null);
        attributesMap.put(SampleMessageGenerator.Platform.APNS_SANDBOX, null);
        attributesMap.put(SampleMessageGenerator.Platform.BAIDU, addBaiduNotificationAttributes());
        attributesMap.put(SampleMessageGenerator.Platform.WNS, addWNSNotificationAttributes());
        attributesMap.put(SampleMessageGenerator.Platform.MPNS, addMPNSNotificationAttributes());
    }

    public static void main(String[] args) throws IOException {
		/*
		 * TODO: Be sure to fill in your AWS access credentials in the
		 * AwsCredentials.properties file before you try to run this sample.
		 * http://aws.amazon.com/security-credentials
		 */
        AmazonSNS sns = new AmazonSNSClient(new PropertiesCredentials(
                SNSMobilePush.class
                        .getResourceAsStream("AwsCredentials.properties")));

        sns.setEndpoint("https://sns.us-east-1.amazonaws.com");
        System.out.println("===========================================\n");
        System.out.println("Getting Started with Amazon SNS");
        System.out.println("===========================================\n");
        try {
            SNSMobilePush sample = new SNSMobilePush(sns);
			/* TODO: Uncomment the services you wish to use. */
            sample.demoAndroidAppNotification();
            // sample.demoKindleAppNotification();
            // sample.demoAppleAppNotification();
            // sample.demoAppleSandboxAppNotification();
            // sample.demoBaiduAppNotification();
            // sample.demoWNSAppNotification();
            // sample.demoMPNSAppNotification();
        } catch (AmazonServiceException ase) {
            System.out
                    .println("Caught an AmazonServiceException, which means your request made it "
                            + "to Amazon SNS, but was rejected with an error response for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out
                    .println("Caught an AmazonClientException, which means the client encountered "
                            + "a serious internal problem while trying to communicate with SNS, such as not "
                            + "being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
    }

    public void demoAndroidAppNotification() {
        // TODO: Please fill in following values for your application. You can
        // also change the notification payload as per your preferences using
        // the method
        // com.amazonaws.sns.samples.tools.SampleMessageGenerator.getSampleAndroidMessage()
        String serverAPIKey = "AIzaSyB93LipUDXsFY6DiUmRMKAcf7iRH4xhR4s";
        String applicationName = "abc";
        String registrationId = "APA91bHAaxnmDDhzNkDpboBVFYEPidu2hT0_dSNR2wqYvd1Zc3Q0-o-HXB06E2lgK_Ueqqxfa2nbxswQAcT6FyXFO4SgeZctUvRuG8uYOn5zS_nTkUwhr8iwijNkqdl59PXMOpVdrsVg";
        snsClientWrapper.demoNotification(SampleMessageGenerator.Platform.GCM, "", serverAPIKey,
                registrationId, applicationName, attributesMap);
    }

    public void demoKindleAppNotification() {
        // TODO: Please fill in following values for your application. You can
        // also change the notification payload as per your preferences using
        // the method
        // com.amazonaws.sns.samples.tools.SampleMessageGenerator.getSampleKindleMessage()
        String clientId = "";
        String clientSecret = "";
        String applicationName = "";

        String registrationId = "";
        snsClientWrapper.demoNotification(SampleMessageGenerator.Platform.ADM, clientId, clientSecret,
                registrationId, applicationName, attributesMap);
    }

    public void demoAppleAppNotification() {
        // TODO: Please fill in following values for your application. You can
        // also change the notification payload as per your preferences using
        // the method
        // com.amazonaws.sns.samples.tools.SampleMessageGenerator.getSampleAppleMessage()
        String certificate = ""; // This should be in pem format with \n at the
        // end of each line.
        String privateKey = ""; // This should be in pem format with \n at the
        // end of each line.
        String applicationName = "";
        String deviceToken = ""; // This is 64 hex characters.
        snsClientWrapper.demoNotification(SampleMessageGenerator.Platform.APNS, certificate,
                privateKey, deviceToken, applicationName, attributesMap);
    }

    public void demoAppleSandboxAppNotification() {
        // TODO: Please fill in following values for your application. You can
        // also change the notification payload as per your preferences using
        // the method
        // com.amazonaws.sns.samples.tools.SampleMessageGenerator.getSampleAppleMessage()
        String certificate = ""; // This should be in pem format with \n at the
        // end of each line.
        String privateKey = ""; // This should be in pem format with \n at the
        // end of each line.
        String applicationName = "";
        String deviceToken = ""; // This is 64 hex characters.
        snsClientWrapper.demoNotification(SampleMessageGenerator.Platform.APNS_SANDBOX, certificate,
                privateKey, deviceToken, applicationName, attributesMap);
    }

    public void demoBaiduAppNotification() {
		/*
		 * TODO: Please fill in the following values for your application. If
		 * you wish to change the properties of your Baidu notification, you can
		 * do so by modifying the attribute values in the method
		 * addBaiduNotificationAttributes() . You can also change the
		 * notification payload as per your preferences using the method
		 * com.amazonaws
		 * .sns.samples.tools.SampleMessageGenerator.getSampleBaiduMessage()
		 */
        String userId = "";
        String channelId = "";
        String apiKey = "";
        String secretKey = "";
        String applicationName = "";
        snsClientWrapper.demoNotification(SampleMessageGenerator.Platform.BAIDU, apiKey, secretKey,
                channelId + "|" + userId, applicationName, attributesMap);
    }

    public void demoWNSAppNotification() {
		/*
		 * TODO: Please fill in the following values for your application. If
		 * you wish to change the properties of your WNS notification, you can
		 * do so by modifying the attribute values in the method
		 * addWNSNotificationAttributes() . You can also change the notification
		 * payload as per your preferences using the method
		 * com.amazonaws.sns.samples
		 * .tools.SampleMessageGenerator.getSampleWNSMessage()
		 */
        String notificationChannelURI = "";
        String packageSecurityIdentifier = "";
        String secretKey = "";
        String applicationName = "";
        snsClientWrapper.demoNotification(SampleMessageGenerator.Platform.WNS,
                packageSecurityIdentifier, secretKey, notificationChannelURI,
                applicationName, attributesMap);
    }

    public void demoMPNSAppNotification() {
		/*
		 * TODO: Please fill in the following values for your application. If
		 * you wish to change the properties of your MPNS notification, you can
		 * do so by modifying the attribute values in the method
		 * addMPNSNotificationAttributes() . You can also change the
		 * notification payload as per your preferences using the method
		 * com.amazonaws
		 * .sns.samples.tools.SampleMessageGenerator.getSampleMPNSMessage ()
		 */
        String notificationChannelURI = "";
        String applicationName = "";
        snsClientWrapper.demoNotification(SampleMessageGenerator.Platform.MPNS, "", "",
                notificationChannelURI, applicationName, attributesMap);
    }

    private static Map<String, MessageAttributeValue> addBaiduNotificationAttributes() {
        Map<String, MessageAttributeValue> notificationAttributes = new HashMap<String, MessageAttributeValue>();
        notificationAttributes.put("AWS.SNS.MOBILE.BAIDU.DeployStatus",
                new MessageAttributeValue().withDataType("String")
                        .withStringValue("1"));
        notificationAttributes.put("AWS.SNS.MOBILE.BAIDU.MessageKey",
                new MessageAttributeValue().withDataType("String")
                        .withStringValue("default-channel-msg-key"));
        notificationAttributes.put("AWS.SNS.MOBILE.BAIDU.MessageType",
                new MessageAttributeValue().withDataType("String")
                        .withStringValue("0"));
        return notificationAttributes;
    }

    private static Map<String, MessageAttributeValue> addWNSNotificationAttributes() {
        Map<String, MessageAttributeValue> notificationAttributes = new HashMap<String, MessageAttributeValue>();
        notificationAttributes.put("AWS.SNS.MOBILE.WNS.CachePolicy",
                new MessageAttributeValue().withDataType("String")
                        .withStringValue("cache"));
        notificationAttributes.put("AWS.SNS.MOBILE.WNS.Type",
                new MessageAttributeValue().withDataType("String")
                        .withStringValue("wns/badge"));
        return notificationAttributes;
    }

    private static Map<String, MessageAttributeValue> addMPNSNotificationAttributes() {
        Map<String, MessageAttributeValue> notificationAttributes = new HashMap<String, MessageAttributeValue>();
        notificationAttributes.put("AWS.SNS.MOBILE.MPNS.Type",
                new MessageAttributeValue().withDataType("String")
                        .withStringValue("token")); // This attribute is required.
        notificationAttributes.put("AWS.SNS.MOBILE.MPNS.NotificationClass",
                new MessageAttributeValue().withDataType("String")
                        .withStringValue("realtime")); // This attribute is required.

        return notificationAttributes;
    }
}
