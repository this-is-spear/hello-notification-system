import http from 'k6/http';
import { check, sleep } from 'k6';
import { URL } from 'https://jslib.k6.io/url/1.0.0/index.js';

export const options = {
    stages: [
        { duration: '10m', target: 500 },
        { duration: '30m', target: 5000 },
        { duration: '5m', target: 0 },
    ],
};

function generateRandomPhoneNumber() {
    const phoneNumbers = ['01012345675', '01012345676', '01012345677', '01012345678']
    return phoneNumbers[Math.floor(Math.random() * phoneNumbers.length)];
}

function selectRandomChannel() {
    const channels = ['KAKAOTALK', 'SMS', 'EMAIL'];
    return channels[Math.floor(Math.random() * channels.length)];
}

export default function () {
    const channel = selectRandomChannel();
    const channelKey = "o";
    const title = "title";
    const content = "test";

    const url = new URL(`http://localhost:8080/send/${channel}`);
    url.searchParams.append('channelKey', channelKey);
    url.searchParams.append('title', title);
    url.searchParams.append('content', content);
    const postResponse = http.post(url.toString());

    check(postResponse, {
        'POST status is 200': (r) => r.status === 200,
    });

    sleep(1);
}
